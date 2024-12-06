package com.example.librarymanagmentsystem;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateTables {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;

        try {
            // Establish database connection
            con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Failed to connect to the database.");
                return;
            }

            st = con.createStatement();

            // Create admin table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS admin (" +
                    "username VARCHAR(100) PRIMARY KEY, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "telephone VARCHAR(20)" +
                    ")");

            // Insert default admin credentials
            st.executeUpdate("INSERT INTO admin (username, password, telephone) " +
                    "VALUES ('admin', 'admin', '123-456-789')");

            // Create books table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS books (" +
                    "book_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "category VARCHAR(100), " +
                    "author VARCHAR(200), " +
                    "copies INT DEFAULT 0" +
                    ")");

            // Create book descriptions table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS book_desc (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "book_id INT, " +
                    "name VARCHAR(255), " +
                    "book_description TEXT, " +
                    "FOREIGN KEY (book_id) REFERENCES books(book_id)" +
                    ")");

            // Create staff table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS staff (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(200), " +
                    "phone_number VARCHAR(20)" +
                    ")");

            // Create customers table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS customers (" +
                    "customer_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "customer_name VARCHAR(200), " +
                    "email VARCHAR(200), " +
                    "phone_number VARCHAR(20)" +
                    ")");

            System.out.println("All tables created and default admin user added successfully!");

        } catch (SQLException e) {
            System.err.println("Error creating tables or inserting default admin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
