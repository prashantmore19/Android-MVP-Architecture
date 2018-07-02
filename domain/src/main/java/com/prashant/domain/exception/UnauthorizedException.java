package com.prashant.domain.exception;

public class UnauthorizedException extends Exception {

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(final String message) {
        super(message);
    }

    public UnauthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(final Throwable cause) {
        super(cause);
    }

}