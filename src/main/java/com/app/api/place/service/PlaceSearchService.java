package com.app.api.place.service;

import com.app.api.place.dto.PlaceSearchApiResponseDto;
import com.app.api.place.dto.PlaceSearchListDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import com.app.domain.place.service.SearchKeywordService;
import com.app.external.place.PlaceExternalType;
import com.app.external.place.PlaceMediator;
import com.app.external.place.PlaceSearchResponseDto;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceSearchService {

    private final PlaceMediator placeMediator;
    private final SearchKeywordService searchKeywordService;

    public PlaceSearchApiResponseDto searchPlace(String keyword, String sort, int token){

        validateKeyword(keyword);

        int size = 5;

        List<PlaceSearchResponseDto> placeSearchs = new ArrayList<>();
        // TODO: for 쓰지말고 워터폴 형태로
        for(PlaceExternalType placeExternalType : PlaceExternalType.values()){
            List<PlaceSearchResponseDto> placeSearchResponseDtos = placeMediator.searchPlaceWithKeyword(placeExternalType, keyword, token, size, sort);
            placeSearchs.addAll(placeSearchResponseDtos);
        }

        placeSearchs = placeSearchs.stream()
                .sorted(Comparator.comparingInt(place -> place.getPlaceExternalType().getOrder()))
                .collect(Collectors.toList());

        List<PlaceSearchListDto> searchList = placeSearchs.stream()
                .map(PlaceSearchListDto::of).collect(Collectors.toList());

        PlaceSearchApiResponseDto result = PlaceSearchApiResponseDto.of(placeSearchs, searchList, sort);

        searchKeywordService.saveOrUpdateSearchCount(keyword);

        return result;
    }

    private void validateKeyword(String keyword) {
        if(!StringUtils.hasText(keyword)) throw new BusinessException(ErrorCode.INVALID_KEYWORD);
    }
    public List<PopularSearchKeywordResponseDto> searchPopularKeyword(){

        return null;
    }
}
