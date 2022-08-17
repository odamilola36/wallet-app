package com.lomari.walletapp.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomException extends Throwable {

    private String message;
    public CustomException(Throwable e) {
        super(e);
        message = e.getLocalizedMessage();
    }

    public CustomException(String message) {
        super(message);
        this.message = message;
    }
}
