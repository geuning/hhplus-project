package com.app.external.place;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceExternalType {
    NAVER(1),
    KAKAO(2),
    GOOGLE(3)
    ;
    private int order;

}
