package com.app.api.place.dto;

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

}
