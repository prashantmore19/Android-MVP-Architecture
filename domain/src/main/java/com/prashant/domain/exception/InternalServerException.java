package com.prashant.domain.exception;

public class InternalServerException extends Exception {

    public InternalServerException() {
        super();
    }

    public InternalServerException(final String message) {
        super(message);
    }

    public InternalServerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(final Throwable cause) {
        super(cause);
    }

}