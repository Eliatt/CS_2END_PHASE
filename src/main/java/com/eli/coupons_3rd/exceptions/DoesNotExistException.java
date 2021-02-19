package com.eli.coupons_3rd.exceptions;

public class DoesNotExistException extends Exception{
    public DoesNotExistException(String message) {
        super(message);
    }
}