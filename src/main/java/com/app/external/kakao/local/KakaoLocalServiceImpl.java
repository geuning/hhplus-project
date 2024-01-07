package com.app.external.kakao.local;

import com.app.external.place.PlaceExternalType;
import com.app.external.place.PlaceSearchResponseDto;
import com.app.external.place.PlaceSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KakaoLocalServiceImpl implements PlaceSearchService {

    private final String kakaoClientId;
    private final KakaoLocalClient kakaoLocalClient;
    private final String PREFIX_AUTHORIZATION = "KakaoAK";

    public KakaoLocalServiceImpl(@Value("${kakao.client.id}") String kakaoClientId, KakaoLocalClient kakaoLocalClient){
        this.kakaoClientId = kakaoClientId;
        this.kakaoLocalClient = kakaoLocalClient;
    }

    @Override
    public Boolean isMyProcess(PlaceExternalType placeExternalType){
        return placeExternalType == PlaceExternalType.KAKAO;
    }

    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int token, int size, String sort){
        if("accuracy".equals(sort)){
            KakaoSearchKeywordDto.Request requestAccuracy = KakaoSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .page(token)
                    .size(size)
                    .sort(KakaoPlaceSearchOrder.정확도순.getValue())
                    .build();
            return getPlaceSearchResponseDtos(token, size, requestAccuracy);

        } else{
            KakaoSearchKeywordDto.Request requestDistance = KakaoSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .page(token)
                    .size(size)
                    .sort(KakaoPlaceSearchOrder.거리순.getValue())
                    .build();

            return getPlaceSearchResponseDtos(token, size, requestDistance);
        }
    }

    private List<PlaceSearchResponseDto> getPlaceSearchResponseDtos(int token, int size, KakaoSearchKeywordDto.Request requestAccuracy) {
        KakaoSearchKeywordDto.Response responseAccuracy = kakaoLocalClient.searchPlaceWithKeyword(PREFIX_AUTHORIZATION + kakaoClientId, requestAccuracy);

        int nextToken = token + size;
        boolean hasNext = !responseAccuracy.getMeta().getIsEnd();

        return responseAccuracy.getDocuments().stream()
                .map(it -> PlaceSearchResponseDto.builder()
                        .placeName(it.getPlaceName())
                        .phoneNumber(it.getPhone())
                        .roadAddress(it.getRoadAddressName())
                        .nextToken(nextToken)
                        .hasNext(hasNext)
                        .placeExternalType(PlaceExternalType.KAKAO)
                        .build())
                .collect(Collectors.toList());
    }
}
