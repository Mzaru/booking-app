package com.mzaru.booking.exception.room;

public class RoomAlreadyExistsException extends RuntimeException {

    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
