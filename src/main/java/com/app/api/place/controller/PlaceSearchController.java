package com.app.api.place.controller;

import com.app.api.place.dto.PlaceSearchApiResponseDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import com.app.api.place.service.PlaceSearchFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceSearchController {

    private final PlaceSearchFacadeService placeSearchFacadeService;

    @GetMapping("/search")
    public ResponseEntity<PlaceSearchApiResponseDto> searchPlace(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "token") int token){
        PlaceSearchApiResponseDto placeSearchApiResponseDto = placeSearchFacadeService.searchPlace(keyword, sort, token);
        return ResponseEntity.ok(placeSearchApiResponseDto);
    }

    @GetMapping("/popular-keyword")
    public ResponseEntity<List<PopularSearchKeywordResponseDto>> searchPopularKeyword(){
        List<PopularSearchKeywordResponseDto> popularSearchKeywordResponseDtos = placeSearchFacadeService.searchPopularKeyword();
        return ResponseEntity.ok(popularSearchKeywordResponseDtos);
    }
}
