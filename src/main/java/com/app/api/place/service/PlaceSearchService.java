package com.app.api.place.service;

import com.app.api.place.dto.PlaceSearchApiResponseDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceSearchService {

    public List<PlaceSearchApiResponseDto> searchPlace(String keyword, String sort, Long nextToken){

        return null;
    }

    public List<PopularSearchKeywordResponseDto> searchPopularKeyword(){

        return null;
    }
}