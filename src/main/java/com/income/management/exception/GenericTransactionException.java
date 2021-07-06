package com.income.management.exception;

public class GenericTransactionException extends Exception {
    public GenericTransactionException(String message) {
        super(message);
    }

    public GenericTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public void printStackTrace() {
    }
}
