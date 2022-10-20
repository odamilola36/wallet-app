package com.lomari.walletapp.utils.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class ApiResponse <T>{
    private String message;
    private HttpStatus status;
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String error;
    private String debugMessage;
    private List<ApiSubError> subErrors;
    private LocalDateTime localDateTime = LocalDateTime.now();

    public ApiResponse() {
    }

    public ApiResponse(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message, HttpStatus status, Throwable cause){
        this.message = message;
        this.status = status;
        this.debugMessage = cause.getMessage()  ;
    }

    public ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(HttpStatus status) {
        this.status = status;
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null){
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }


    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(err -> {
            FieldError error1 = (FieldError) err;
            addValidationError(error1.getObjectName(), error1.getField(), error1.getRejectedValue(), error1.getDefaultMessage());
        });
    }

}
