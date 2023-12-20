package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddBookController {
    // FXML annotations for the UI elements
    @FXML
    private TextField authorTF;

    @FXML
    private Button backBTN,addBTN;

    @FXML
    private TextField categoryTF;

    @FXML
    private TextField copiesTF;

    @FXML
    private TextField nameTF;

    @FXML
    public void backButton(ActionEvent event) throws IOException { // Switch to the homepage when the back button is pressed
        new SwitchView("Homepage.fxml",event);
    }

    @FXML
    void addBook(ActionEvent event) { // Add a new book when the "Add" button is pressed
        Connection con = DBConnection.getConnection(); // Establish a database connection
        if (con == null) {
            new ShowMessageDialog("Database Connection Error","There was an error connecting to the database.");
        }
        Book book = new Book(); // Create a Book object with values from text fields
        book.setBookName(nameTF.getText());
        book.setCategory(categoryTF.getText());
        book.setAuthor(authorTF.getText());
        book.setCopies(Integer.parseInt(copiesTF.getText()));
        String query = "INSERT INTO books (category, name, author, copies) VALUES (?,?,?,?);"; // SQL query to insert a new book into the database
        try (PreparedStatement preparedStatement = con.prepareStatement(query)){
            // Set parameters for the prepared statement
            preparedStatement.setString(1, book.getCategory());
            preparedStatement.setString(2, book.getBookName());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getCopies());
            preparedStatement.executeUpdate(); // Execute the update query
            new ShowMessageDialog("Done","Book Added Successfully"); // Execute the update query and Clear text fields after adding book
            authorTF.setText("");
            categoryTF.setText("");
            copiesTF.setText("");
            nameTF.setText("");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
