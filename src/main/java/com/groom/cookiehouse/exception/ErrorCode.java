package com.groom.cookiehouse.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 404 BAD REQUEST
     * */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 헤더값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터값이 입력되지 않았습니다."),
    REQUEST_METHOD_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 메소드가 잘못됐습니다."),
    MAX_UPLOAD_SIZE_EXCEED_EXCEPTION(HttpStatus.PAYLOAD_TOO_LARGE, "파일 용량 초과"),
    ALREADY_MISSION_COMPLETE(HttpStatus.BAD_REQUEST, "이미 미션을 수행하였습니다"),

    /**
     * 404 NOT FOUND
     * */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NOT_FOUND_ICING_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 아이싱입니다."),
    NOT_FOUND_COOKIE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 쿠키입니다."),
    NOT_FOUND_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 이미지입니다."),
    NOT_FOUND_MISSION_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 날짜의 미션입니다."),
    NOT_FOUND_FURNITURE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 가구입니다."),
    NOT_FOUND_MISSION_COMPLETE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 미션 수행입니다"),

    /**
     * 401 UNAUTHORIZED
     */
    TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    DELETE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "삭제 요청 권한이 없습니다."),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}