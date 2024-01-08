package com.app.api.place.dto;

import com.app.external.place.PlaceSearchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaceSearchListDto {

    private String placeName;
    private String roadAddress;
    private String phoneNumber;

    public static PlaceSearchListDto of(PlaceSearchResponseDto placeSearchResponseDto) {
        return PlaceSearchListDto.builder()
                .placeName(placeSearchResponseDto.getPlaceName())
                .roadAddress(placeSearchResponseDto.getRoadAddress())
                .phoneNumber(placeSearchResponseDto.getPhoneNumber())
                .build();

    }


}
