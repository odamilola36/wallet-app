package com.lomari.walletapp.aop;

import com.lomari.walletapp.utils.ResponseBuilder;
import com.lomari.walletapp.utils.payload.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class ValidationsExceptionHandler {

    private static final String FAILED = "failed";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        ApiResponse<Object> response = new ApiResponse<>(FAILED, HttpStatus.BAD_REQUEST, ex);
        response.addValidationError(allErrors);
        return ResponseBuilder.buildApiResponse(response);
    }

    // TODO: Flesh this out with more details about the user and endpoint
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        String authorization = request.getHeader("Authorization");

        return new ResponseEntity<Object>(
                "Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

}
