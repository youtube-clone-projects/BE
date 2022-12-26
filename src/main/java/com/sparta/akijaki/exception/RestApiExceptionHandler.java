package com.sparta.akijaki.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleApiRequestException(IllegalArgumentException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        restApiException.setErrorMessage(ex.getMessage());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleApiRequestException(MethodArgumentNotValidException ex) {
        RestApiException restApiException = new RestApiException();
        restApiException.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        restApiException.setErrorMessage(ex.getFieldError().getDefaultMessage());
        return new ResponseEntity(restApiException, HttpStatus.BAD_REQUEST);
    }
}
