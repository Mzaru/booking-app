package com.mzaru.booking.controller.ExceptionHandler;

import com.mzaru.booking.dto.ErrorDto;
import com.mzaru.booking.exception.booking.BookingPeriodNotAvailableException;
import com.mzaru.booking.exception.room.RoomAlreadyExistsException;
import com.mzaru.booking.exception.room.RoomNotFoundException;
import com.mzaru.booking.exception.user.UserNotFoundException;
import com.mzaru.booking.exception.WrongAdminPasswordException;
import com.mzaru.booking.exception.user.WrongUserLoginOrPasswordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class BookingResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), "Validation Failed",
                ex.getMessage());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongAdminPasswordException.class)
    public final ResponseEntity<ErrorDto> handleWrongAdminPasswordException(WrongAdminPasswordException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomAlreadyExistsException.class)
    public final ResponseEntity<ErrorDto> handleRoomAlreadyExistsException(RoomAlreadyExistsException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public final ResponseEntity<ErrorDto> handleRoomNotFoundException(RoomNotFoundException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingPeriodNotAvailableException.class)
    public final ResponseEntity<ErrorDto> handleBookingPeriodNotAvailableException(BookingPeriodNotAvailableException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongUserLoginOrPasswordException.class)
    public final ResponseEntity<ErrorDto> handleWrongUserLoginOrPasswordException(WrongUserLoginOrPasswordException ex, WebRequest request) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }
}
