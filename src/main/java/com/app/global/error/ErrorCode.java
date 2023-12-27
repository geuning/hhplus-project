package com.app.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 공통
    SERVER_ERROR("COMMON-001", "요청을 처리하는 중 오류가 발생하였습니다. 관리자에게 문의해주세요"),
    // 장소
    INVALID_KEYWORD("PLACE-001", "장소 검색 키워드는 필수 값 입니다."),
    NO_MORE_PLACE("PLACE-002", "더 이상 검색 결과가 없습니다.")

    ;






    ErrorCode(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    private String errorCode;
    private String message;
}
