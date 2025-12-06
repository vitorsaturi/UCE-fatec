package br.edu.fatecfranca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static String URL = "jdbc:postgresql://localhost:5432/PI-biblioteca";
    static String USER = "postgres";
    static String PASSWORD = "fatec123*";

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}