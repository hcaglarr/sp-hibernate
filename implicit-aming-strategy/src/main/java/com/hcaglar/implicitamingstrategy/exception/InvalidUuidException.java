package com.hcaglar.implicitamingstrategy.exception;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException(String message) {
        super(message);
    }

    public InvalidUuidException(String message, Throwable cause) {
        super(message, cause);
    }
}
