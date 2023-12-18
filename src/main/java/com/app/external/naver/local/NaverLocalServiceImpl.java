package com.app.external.naver.local;

import com.app.external.place.PlaceSearchResponseDto;
import com.app.external.place.PlaceSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword) {
        return null;
    }







}
