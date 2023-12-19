package com.app.external.kakao.local;

import com.app.external.place.PlaceSearchResponseDto;
import com.app.external.place.PlaceSearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int page, int size){
        return null;

    }
}
