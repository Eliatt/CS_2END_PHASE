package com.eli.coupons_3rd.rest;

import com.eli.coupons_3rd.exceptions.FailOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllersExceptionHandler {
    @ExceptionHandler({FailOperationException.class})
    public ResponseEntity<ErrorMessage> handleException(Exception e) {
        return new ResponseEntity<>(new ErrorMessage(e), HttpStatus.BAD_REQUEST);
    }
}
