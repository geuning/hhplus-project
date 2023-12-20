package com.app.api.place.service;

import com.app.api.place.dto.PlaceSearchApiResponseDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import com.app.external.place.PlaceExternalType;
import com.app.external.place.PlaceMediator;
import com.app.external.place.PlaceSearchResponseDto;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceSearchService {

    private final PlaceMediator placeMediator;
//    private final SearchKeywordService searchKeywordService;

    public List<PlaceSearchApiResponseDto> searchPlace(String keyword, String sort, int nextToken){

        validateKeyword(keyword);

        int size = 5;

        List<PlaceSearchResponseDto> placeSearchs = new ArrayList<>();
        for(PlaceExternalType placeExternalType : PlaceExternalType.values()){
            List<PlaceSearchResponseDto> placeSearchResponseDtos = placeMediator.searchPlaceWithKeyword(placeExternalType, keyword, nextToken, size, sort);
        }



        return null;
    }

    private void validateKeyword(String keyword) {
        if(!StringUtils.hasText(keyword)) throw new BusinessException(ErrorCode.INVALID_KEYWORD);
    }
    public List<PopularSearchKeywordResponseDto> searchPopularKeyword(){

        return null;
    }
}
