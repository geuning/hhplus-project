package com.app.global.error;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    @Builder(access = AccessLevel.PRIVATE)
    private ErrorResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public static ErrorResponse of(String errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return ErrorResponse.builder()
                .errorCode(errorCode.getErrorCode())
                .errorMessage(errorCode.getMessage())
                .build();
    }




}
