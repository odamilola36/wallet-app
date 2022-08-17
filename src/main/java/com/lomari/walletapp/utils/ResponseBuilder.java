package com.lomari.walletapp.utils;

import com.lomari.walletapp.utils.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    public static <T> ResponseEntity<Object> buildErrorResponse(ApiResponse<T> response) {
        return new ResponseEntity<>(response, response.getStatus());
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildApiResponse(ApiResponse<T> response){
        return new ResponseEntity<>(response, response.getStatus());
    }
}
