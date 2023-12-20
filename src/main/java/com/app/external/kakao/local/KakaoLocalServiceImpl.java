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

    @Override
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int page, int size, String sort){
        KakaoSearchKeywordDto.Request request = KakaoSearchKeywordDto.Request.builder()
                .query(keyword)
                .page(page)
                .size(size)
                .build();

        if ("accuracy".equals(sort)) {
            request.builder().sort(KakaoPlaceSearchOrder.정확도순.getValue());
        } else {
            request.builder().sort(KakaoPlaceSearchOrder.거리순.getValue());
        }

        KakaoSearchKeywordDto.Response response = kakaoLocalClient.searchPlaceWithKeyword(PREFIX_AUTHORIZATION + kakaoClientId, request);

        return response.getDocuments().stream()
                .map(it -> PlaceSearchResponseDto.builder()
                        .placeName(it.getPlaceName())
                        .phoneNumber(it.getPhone())
                        .roadAddress(it.getRoadAddressName())
                        .placeExternalType(PlaceExternalType.KAKAO)
                        .build())
                .collect(Collectors.toList());
    }
}
