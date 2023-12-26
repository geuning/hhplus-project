package com.app.external.place;

import java.util.List;

public interface PlaceSearchService {

    List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword, int token, int size, String sort);

    Boolean isMyProcess(PlaceExternalType placeExternalType);
}
