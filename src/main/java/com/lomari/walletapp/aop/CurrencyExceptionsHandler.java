package com.lomari.walletapp.aop;

import com.lomari.walletapp.exceptions.InvalidCurrencyException;
import com.lomari.walletapp.utils.ResponseBuilder;
import com.lomari.walletapp.utils.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CurrencyExceptionsHandler {
    private static final String FAILED = "failed";

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserNotFoundException(UsernameNotFoundException ex){
        ApiResponse<Object> response = new ApiResponse<>(FAILED, HttpStatus.NOT_FOUND, ex);
        return ResponseBuilder.buildApiResponse(response);
    }
}
