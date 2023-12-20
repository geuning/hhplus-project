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

    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int page, int size, String sort){
        if("accuracy".equals(sort)){
            KakaoSearchKeywordDto.Request request1 = KakaoSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .page(page)
                    .size(size)
                    .sort(KakaoPlaceSearchOrder.정확도순.getValue())
                    .build();

            KakaoSearchKeywordDto.Response response1 = kakaoLocalClient.searchPlaceWithKeyword(PREFIX_AUTHORIZATION + kakaoClientId, request1);
            return response1.getDocuments().stream()
                    .map(it -> PlaceSearchResponseDto.builder()
                            .placeName(it.getPlaceName())
                            .phoneNumber(it.getPhone())
                            .roadAddress(it.getRoadAddressName())
                            .placeExternalType(PlaceExternalType.KAKAO)
                            .build())
                    .collect(Collectors.toList());

        } else{
            KakaoSearchKeywordDto.Request request2 = KakaoSearchKeywordDto.Request.builder()
                    .query(keyword)
                    .page(page)
                    .size(size)
                    .sort(KakaoPlaceSearchOrder.거리순.getValue())
                    .build();


            KakaoSearchKeywordDto.Response response2 = kakaoLocalClient.searchPlaceWithKeyword(PREFIX_AUTHORIZATION + kakaoClientId, request2);

            return response2.getDocuments().stream()
                    .map(it -> PlaceSearchResponseDto.builder()
                            .placeName(it.getPlaceName())
                            .phoneNumber(it.getPhone())
                            .roadAddress(it.getRoadAddressName())
                            .placeExternalType(PlaceExternalType.KAKAO)
                            .build())
                    .collect(Collectors.toList());
        }
    }
}
