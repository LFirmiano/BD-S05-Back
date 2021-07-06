package com.income.management.conf;

import com.income.management.exception.SQLConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    private Connection sqlConnection;

    /**
     * Get SQL Connection.
     *
     * @return Connection
     * @throws SQLConnectionException ex
     */
    public Connection getConnection() throws SQLConnectionException {
//        Connection con;
//        try {
//            con = DriverManager.getConnection(jdbcUrl);
//        } catch (SQLException throwable) {
//            throw new SQLConnectionException(throwable.getMessage(), throwable);
//        }
//        return con;

        return sqlConnection;
    }

    @Bean
    void setConnection() throws SQLConnectionException {
        try {
            this.sqlConnection = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException throwable) {
            throw new SQLConnectionException(throwable.getMessage(), throwable);
        }
    }
}
