package com.groom.cookiehouse.exception.model;

import com.groom.cookiehouse.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode error, String message) {
        super(error, message);
    }
}
