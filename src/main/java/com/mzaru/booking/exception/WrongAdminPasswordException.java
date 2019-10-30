package com.mzaru.booking.exception;

public class WrongAdminPasswordException extends RuntimeException {

    public WrongAdminPasswordException(String message) {
        super(message);
    }
}
