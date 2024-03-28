package com.example.demo.error;

public class UnknownApplicationTypeException extends Exception {

    public UnknownApplicationTypeException() {
        super();
    }

    public UnknownApplicationTypeException(String message) {
        super(message);
    }

    public UnknownApplicationTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownApplicationTypeException(Throwable cause) {
        super(cause);
    }

    protected UnknownApplicationTypeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
