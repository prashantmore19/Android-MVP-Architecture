package com.prashant.domain.exception;

public class UnknownException extends Exception {

    public UnknownException() {
        super();
    }

    public UnknownException(final String message) {
        super(message);
    }

    public UnknownException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownException(final Throwable cause) {
        super(cause);
    }

}