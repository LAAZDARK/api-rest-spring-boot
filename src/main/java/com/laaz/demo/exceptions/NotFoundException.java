package com.laaz.demo.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final int errorCode;

    public NotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
