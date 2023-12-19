package com.app.external.kakao.local;

import lombok.Getter;

@Getter
public enum KakaoPlaceSearchOrder {

    거리순("distance"),
    정확도순("accuracy")
    ;

    private String value;

    KakaoPlaceSearchOrder(String value){
        this.value = value;
    }
}
