package com.mzaru.booking.validator;

import com.mzaru.booking.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Component
public class UserValidator {

    public void validate(@Valid User user) {}
}
