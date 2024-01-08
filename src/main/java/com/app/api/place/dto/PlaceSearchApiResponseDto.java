package com.app.api.place.dto;

import com.app.external.place.PlaceSearchResponseDto;
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

    private int nextToken;
    private boolean hasNext;
    private String sort;
    private List<PlaceSearchListDto> placeSearchListDtos;

    public static PlaceSearchApiResponseDto of(List<PlaceSearchResponseDto> placeSearchResponseDtos,
                                               List<PlaceSearchListDto> placeSearchListDtos,
                                               String sort) {
        return PlaceSearchApiResponseDto.builder()
                .nextToken(placeSearchResponseDtos.get(0).getNextToken())
                .hasNext(placeSearchResponseDtos.get(0).isHasNext())
                .sort(sort)
                .placeSearchListDtos(placeSearchListDtos)
                .build();
    }


}
