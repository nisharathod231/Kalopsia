package com.kalopsia.backend.exception;

public class UnAuthorisedException extends RuntimeException {
    public UnAuthorisedException(String message) {
        super(message);
    }

    public UnAuthorisedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnAuthorisedException(Throwable cause) {
        super(cause);
    }
}
