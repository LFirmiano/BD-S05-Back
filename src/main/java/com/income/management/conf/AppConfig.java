package com.income.management.conf;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import com.income.management.exception.SQLConnectionException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class AppConfig {
    @Bean
    public CommandLineRunner tete() {
        return (args) -> {
            System.out.println("I am a runner");
        };
    }

    /**
     * Get SQL Connection.
     *
     * @return Connection
     * @throws SQLConnectionException ex
     */
//    @Bean
    public Connection getConnection() throws SQLConnectionException {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/IncomeManagement?"
                    + "user=root&password=sena2001");
        } catch (SQLException throwable) {
            throw new SQLConnectionException(throwable.getMessage(), throwable);
        }

        return con;
    }

    /**
     * Second way to get SQL Connection;
     * Get SQL Connection.
     */
    public void getDataSource() {
        System.out.println("I am a Bean");
        DriverManagerConnectionSource dataSource = new DriverManagerConnectionSource();

        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");

        dataSource.setUrl("jdbc:mysql://localhost/IncomeManagement");

        dataSource.setUser("root");
        dataSource.setPassword("sena2001");

        try {
            Connection con = dataSource.getConnection();

            Statement stm = con.createStatement();
            var res = stm.executeQuery("SHOW TABLES");
            while (res.next()) System.out.println(res.getString("Tables_in_IncomeManagement"));
            res.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
