package com.hcaglar.implicitamingstrategy.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
public class GlobalException {
    private HttpStatus m_status;
    private String m_message;
    private ZonedDateTime m_timestamp;

    public GlobalException(HttpStatus status, String message, ZonedDateTime timestamp) {
        m_status = status;
        m_message = message;
        m_timestamp = timestamp;
    }

    public static GlobalExceptionBuilder builder() {
        return new GlobalExceptionBuilder();
    }

    public HttpStatus getStatus() {
        return m_status;
    }

    public String getMessage() {
        return m_message;
    }

    public ZonedDateTime gettimestamp() {
        return m_timestamp;
    }

    public void setStatus(HttpStatus status) {
        m_status = status;
    }

    public void setMessage(String message) {
        m_message = message;
    }

    public void settimestamp(ZonedDateTime timestamp) {
        m_timestamp = timestamp;
    }

    public static class GlobalExceptionBuilder {
        private HttpStatus m_status;
        private String m_message;
        private ZonedDateTime m_timestamp;

        GlobalExceptionBuilder() {
        }

        public GlobalExceptionBuilder status(HttpStatus status) {
            m_status = status;
            return this;
        }

        public GlobalExceptionBuilder message(String message) {
            m_message = message;
            return this;
        }

        public GlobalExceptionBuilder timestamp(ZonedDateTime timestamp) {
            m_timestamp = timestamp;
            return this;
        }

        public GlobalException build() {
            return new GlobalException(m_status, m_message, m_timestamp);
        }
    }
}
