package com.prashant.domain.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(final String message) {
        super(message);
    }

    public ForbiddenException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(final Throwable cause) {
        super(cause);
    }

}