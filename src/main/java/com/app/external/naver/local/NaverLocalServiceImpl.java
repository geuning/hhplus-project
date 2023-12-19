package com.app.external.naver.local;

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

    // TODO: sort 분기처리
    @Override
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int page, int size) {
        NaverSearchKeywordDto.Request request = NaverSearchKeywordDto.Request.builder()
                .query(keyword)
                .start(page)
                .display(size)
                .sort(NaverPlaceSearchOrder.인기순.getValue())
                .build();

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
