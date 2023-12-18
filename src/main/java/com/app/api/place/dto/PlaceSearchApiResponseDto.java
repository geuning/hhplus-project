package com.app.api.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceSearchApiResponseDto {

    private Long nextToken;
    private Boolean hasNext;
    private String sort;
    private List<PlaceSearchListDto> placeSearchListDtos;


}
