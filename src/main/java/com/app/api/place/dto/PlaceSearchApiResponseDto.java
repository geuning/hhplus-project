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

    private Long nextToken;
    private Boolean hasNext;
    private String sort;
    private List<PlaceSearchListDto> placeSearchListDtos;

    public static PlaceSearchApiResponseDto of(PlaceSearchResponseDto placeSearchResponseDto) {
        return PlaceSearchApiResponseDto.builder()
                .placeName(StringTagUtils.removeHtmlTags(placeSearchResponseDto.getPlaceName()))
                .roadAddress(placeSearchResponseDto.getRoadAddress())
                .phoneNumber(placeSearchResponseDto.getPhoneNumber())
                .build();
    }


}
