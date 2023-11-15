package com.groom.cookiehouse.common.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.groom.cookiehouse.exception.ErrorCode;
import com.groom.cookiehouse.exception.SuccessCode;
import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BaseResponse<T> {

    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(SuccessCode success) {
        return new BaseResponse<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode success, T data) {
        return new BaseResponse<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static BaseResponse error(ErrorCode error) {
        return new BaseResponse<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static BaseResponse error(ErrorCode error, String message) {
        return new BaseResponse<>(error.getHttpStatusCode(), message);
    }

    public static BaseResponse<Void> ok() {
        return BaseResponse.<Void>builder()
                .code(200)
                .message("요청이 정상적으로 수행되었습니다.")
                .build();
    }

    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .code(200)
                .message("요청이 정상적으로 수행되었습니다.")
                .data(data)
                .build();
    }

}
