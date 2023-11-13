package com.groom.cookiehouse.exception.model;

import com.groom.cookiehouse.exception.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode error, String message) {
        super(error, message);
    }
}