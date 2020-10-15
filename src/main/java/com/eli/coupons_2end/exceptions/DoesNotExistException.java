package com.eli.coupons_2end.exceptions;

public class DoesNotExistException extends Exception{
    public DoesNotExistException(String message) {
        super(message);
    }
}