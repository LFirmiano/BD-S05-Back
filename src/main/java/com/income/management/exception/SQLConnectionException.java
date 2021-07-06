package com.income.management.exception;

import java.sql.SQLException;

public class SQLConnectionException extends Exception {
    public SQLConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
