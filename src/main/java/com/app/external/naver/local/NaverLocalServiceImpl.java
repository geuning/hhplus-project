package com.app.external.naver.local;

import com.app.external.kakao.local.KakaoPlaceSearchOrder;
import com.app.external.place.PlaceExternalType;
import com.app.external.place.PlaceSearchResponseDto;
import com.app.external.place.PlaceSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    @Override
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int page, int size, String sort) {
        NaverSearchKeywordDto.Request request = NaverSearchKeywordDto.Request.builder()
                .query(keyword)
                .start(page)
                .display(size)
                .build();

        if ("accuracy".equals(sort)) {
            request.builder().sort(NaverPlaceSearchOrder.정확도순.getValue());
        } else {
            request.builder().sort(NaverPlaceSearchOrder.인기순.getValue());
        }

        NaverSearchKeywordDto.Response response = naverLocalClient.searchPlaceWithKeyword(naverClientId, naverSecretId, request);


        return response.getItems().stream()
                .map(it ->PlaceSearchResponseDto.builder()
                        .placeName(it.getTitle())
                        .phoneNumber(it.getTelephone())
                        .roadAddress(it.getRoadAddress())
                        .placeExternalType(PlaceExternalType.NAVER)
                        .build())
                .collect(Collectors.toList());
    }
}
