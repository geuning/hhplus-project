package com.app.api.place.controller;

import com.app.api.place.dto.PlaceSearchApiResponseDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import com.app.api.place.service.PlaceSearchService;
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

    private final PlaceSearchService placeSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<PlaceSearchApiResponseDto>> searchPlace(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "nextToken") int nextToken){
        List<PlaceSearchApiResponseDto> placeSearchApiResponseDtos = placeSearchService.searchPlace(keyword, sort, nextToken);
        return ResponseEntity.ok(placeSearchApiResponseDtos);
    }

    @GetMapping("/popular-keyword")
    public ResponseEntity<List<PopularSearchKeywordResponseDto>> searchPopularKeyword(){
        List<PopularSearchKeywordResponseDto> popularSearchKeywordResponseDtos = placeSearchService.searchPopularKeyword();
        return ResponseEntity.ok(popularSearchKeywordResponseDtos);
    }
}
