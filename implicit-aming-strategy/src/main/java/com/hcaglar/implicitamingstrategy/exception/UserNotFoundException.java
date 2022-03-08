package com.hcaglar.implicitamingstrategy.exception;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
