package com.mzaru.booking.validator;

import com.mzaru.booking.entity.Room;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Component
public class RoomValidator {

    public void validate(@Valid Room room) {}
}
