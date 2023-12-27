package com.app.external.naver.local;

import com.app.external.kakao.local.KakaoPlaceSearchOrder;
import com.app.external.place.PlaceExternalType;
import com.app.external.place.PlaceSearchResponseDto;
import com.app.external.place.PlaceSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NaverLocalServiceImpl implements PlaceSearchService {

    private final String naverClientId;
    private final String naverSecretId;
    private final NaverLocalClient naverLocalClient;

    public NaverLocalServiceImpl(
            @Value("${naver.client.id}") String naverClientId,
            @Value("${naver.client.secret}") String naverSecretId, NaverLocalClient naverLocalClient){
        this.naverClientId = naverClientId;
        this.naverSecretId = naverSecretId;
        this.naverLocalClient = naverLocalClient;
    }

    @Override
    public Boolean isMyProcess(PlaceExternalType placeExternalType){
        return placeExternalType == PlaceExternalType.NAVER;
    }

    // TODO: sort 분기처리 , 파라미터 sort추가, 빈값일때 빈리스트 보내주기, 서비스에서 받아서 네이번지 카카온지 어케알수있으까
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int token, int size, String sort) {
        if("accuracy".equals(sort)){
            NaverSearchKeywordDto.Request requestAccuracy = NaverSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .start(token)
                    .display(size)
                    .sort(NaverPlaceSearchOrder.정확도순.getValue())
                    .build();
            return getPlaceSearchResponseDtos(requestAccuracy);

        } else {
            NaverSearchKeywordDto.Request requestPopular = NaverSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .start(token)
                    .display(size)
                    .sort(NaverPlaceSearchOrder.인기순.getValue())
                    .build();
            return getPlaceSearchResponseDtos(requestPopular);
        }
    }

    private List<PlaceSearchResponseDto> getPlaceSearchResponseDtos(NaverSearchKeywordDto.Request requestAccuracy) {
        NaverSearchKeywordDto.Response responseAccuracy = naverLocalClient.searchPlaceWithKeyword(naverClientId, naverSecretId, requestAccuracy);
        // TODO: nextToken 없는경우는? hasNext없는경우는? naver는 total값 제공
        int nextToken = (int) (responseAccuracy.getStart() + responseAccuracy.getDisplay());

        if (responseAccuracy.getTotal() < nextToken) {
            throw new BusinessException(ErrorCode.NO_MORE_PLACE);
        }

        return responseAccuracy.getItems().stream()
                .map(it ->PlaceSearchResponseDto.builder()
                        .placeName(it.getTitle())
                        .phoneNumber(it.getTelephone())
                        .roadAddress(it.getRoadAddress())
                        .nextToken(nextToken)
                        .placeExternalType(PlaceExternalType.NAVER)
                        .build())
                .collect(Collectors.toList());
    }
}
