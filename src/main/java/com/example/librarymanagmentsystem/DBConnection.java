package com.example.librarymanagmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String DB_NAME = "library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;         // A static variable to hold the database connection
    public static Connection getConnection() {    // Method to get a connection to the database
        try { // Attempt to establish a connection using JDBC
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME), USERNAME, PASSWORD);
            System.out.println("Database is connected");  // Print a message in console indicating successful connection
        } catch(SQLException se) {
            se.printStackTrace();
        }
        return connection; // Return the established connection or null if unsuccessful
    }
}
