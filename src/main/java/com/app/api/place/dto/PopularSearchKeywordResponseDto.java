package com.app.api.place.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopularSearchKeywordResponseDto {

    private String keyword;
    private Long searchCount;
}
