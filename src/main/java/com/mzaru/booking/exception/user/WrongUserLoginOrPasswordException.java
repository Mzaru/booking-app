package com.mzaru.booking.exception.user;

public class WrongUserLoginOrPasswordException extends RuntimeException {

    public WrongUserLoginOrPasswordException(String message) {
        super(message);
    }
}
