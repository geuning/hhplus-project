package com.app.external.place;

import java.util.List;

public interface PlaceSearchService {

    List<PlaceSearchResponseDto> searchPlaceWithKeyword(String keyword);
}
