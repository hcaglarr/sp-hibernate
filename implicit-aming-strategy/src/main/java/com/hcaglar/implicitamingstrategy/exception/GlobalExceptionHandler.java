package com.hcaglar.implicitamingstrategy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GlobalException> exceptionHandler(UserNotFoundException ex){
        final var httpStatus = HttpStatus.NOT_FOUND;
        final var globalException = GlobalException
                                                .builder()
                                                .status(httpStatus)
                                                .message(ex.getMessage())
                                                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                                                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(globalException);
    }

    @ExceptionHandler(InvalidUuidException.class)
    public ResponseEntity<GlobalException> exceptionHandler(InvalidUuidException ex){
        final var httpStatus = HttpStatus.BAD_REQUEST;
        final var globalException = GlobalException
                .builder()
                .status(httpStatus)
                .message(ex.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(globalException);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GlobalException> exceptionHandler(EntityNotFoundException ex){
        final var httpStatus = HttpStatus.NOT_FOUND;
        final var globalException = GlobalException
                .builder()
                .status(httpStatus)
                .message(ex.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Z")))
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(globalException);
    }

}
