package com.example.commers.config.secret;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    // Common
    EMPTY_JWT(false, 2001, "로그인이 필요한 서비스 입니다."),
    NO_AUTH_TO_CART(false, 2002, "장바구니 접근 권한이 없습니다."),

    NO_CART(false,2003,"장바구니가 없습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
