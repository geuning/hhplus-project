package com.app.external.place;


import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlaceMediator {

    private final Map<String, PlaceSearchService> placeSearchServiceMap;

    // TODO: sort 거리순, 정확도순일때 네이버 카카오 어떻게 뿌려줄지
    public List<PlaceSearchResponseDto> searchPlaceWithKeyword(PlaceExternalType placeExternalType, String keyword, int nextToken, int size, String sort){
        for(PlaceSearchService placeSearchService : placeSearchServiceMap.values()){
            if(placeSearchService.isMyProcess(placeExternalType)){
                try {
                    return placeSearchService.searchPlaceWithKeyword(keyword, nextToken, size, sort);
                } catch (Exception e){
                    return new ArrayList<>();
                }
            }
        }
        // TODO: 코드값 변경
        throw new BusinessException(ErrorCode.SERVER_ERROR);
    }
}
