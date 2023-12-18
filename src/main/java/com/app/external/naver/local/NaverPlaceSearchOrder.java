package com.app.external.naver.local;

import lombok.Getter;

@Getter
public enum NaverPlaceSearchOrder {

    인기순("comment"),
    정확도순("random")
    ;

    private String value;

    NaverPlaceSearchOrder(String value){
        this.value = value;
    }
}
