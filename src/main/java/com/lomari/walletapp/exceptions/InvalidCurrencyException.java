package com.lomari.walletapp.exceptions;

public class InvalidCurrencyException extends RuntimeException {

    private  String message;

    public InvalidCurrencyException(String message) {
        super(message);
        this.message = message;
    }
}
