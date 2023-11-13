package com.groom.cookiehouse.exception.model;

import com.groom.cookiehouse.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode error, String message) {
        super(error, message);
    }
}
