package com.app.external.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSearchResponseDto {
    private String placeName;
    private String roadAddress;
    private String phoneNumber;
    private int nextToken;
    private Boolean hasNext;
    private PlaceExternalType placeExternalType;
}
