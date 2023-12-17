package com.app.api.place.controller;

import com.app.api.place.dto.PlaceSearchResponseDto;
import com.app.api.place.dto.PopularSearchKeywordResponseDto;
import com.app.domain.place.service.PlaceSearchService;
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
    public ResponseEntity<List<PlaceSearchResponseDto>> searchPlace(@RequestParam(value = "keyword") String keyword){
        List<PlaceSearchResponseDto> placeSearchResponseDtos = placeSearchService.searchPlace(keyword);
        return ResponseEntity.ok(placeSearchResponseDtos);
    }

    @GetMapping("/popular-keyword")
    public ResponseEntity<List<PopularSearchKeywordResponseDto>> searchPopularKeyword(){
        List<PopularSearchKeywordResponseDto> popularSearchKeywordResponseDtos = placeSearchService.searchPopularKeyword();
        return ResponseEntity.ok(popularSearchKeywordResponseDtos);
    }
}
