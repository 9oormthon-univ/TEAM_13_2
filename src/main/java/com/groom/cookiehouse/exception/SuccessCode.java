package com.groom.cookiehouse.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    GET_HOUSE_SUCCESS(HttpStatus.OK, "집을 성공적으로 조회했습니다."),
    GET_MISSION_SUCCESS(HttpStatus.OK, "미션을 성공적으로 조회했습니다."),
    RE_ISSUE_TOKEN_SUCCESS(HttpStatus.OK, "토큰 재발급을 성공했습니다"),
    GET_ALL_GUESTBOOK_SUCCESS(HttpStatus.OK, "방명록 조회에 성공했습니다"),
    GET_MISSION_COMPLETE_SUCCESS(HttpStatus.OK, "미션 수행 내역을 조회했습니다"),
    GET_ALL_MISSON_COMPLETE_SUCCESS(HttpStatus.OK, "미션 수행 내역을 모두 조회했습니다"),
    GET_USER_INFO_SUCCESS(HttpStatus.OK, "사용자 정보 조회에 성공했습니다"),

    /**
     * 201 CREATED
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
    SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
    SIGNOUT_SUCCESS(HttpStatus.CREATED, "로그아웃이 완료됐습니다."),
    HOUSE_CREATED_SUCCESS(HttpStatus.CREATED, "집이 완성되었습니다"),
    MISSION_COMPLETE_CREATED_SUCCESS(HttpStatus.CREATED, "미션을 성공적으로 수행하였습니다"),
    MISSION_COMPLETE_UPDATED_SUCCESS(HttpStatus.CREATED, "미션을 성공적으로 수정하였습니다."),
    GUESTBOOK_CREATE_SUCCESS(HttpStatus.CREATED, "방명록을 성공적으로 등록하였습니다.")
    ;


    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
