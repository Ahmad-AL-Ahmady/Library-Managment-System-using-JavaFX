package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class AddBookDescriptionController {
    // Flag to check if the book ID exists
    boolean flag = false;

    // FXML annotations to inject components from the FXML file
    @FXML
    private TextArea TA;

    @FXML
    private TextField TF;

    @FXML
    private Button addDesc, backBTN;

    // Method to handle the event when the "Add Description" button is clicked
    @FXML
    public void addDesc(ActionEvent event) throws IOException {
        // Get a connection to the database
        Connection con = DBConnection.getConnection();
        if (con == null) {
            // Display an error message if the connection fails
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
        }

        // Create instances of Book and BookDesc to store book and description information
        Book book = new Book();
        BookDesc bookDesc = new BookDesc();

        // Set the book ID from the text field
        book.setBookId(Integer.parseInt(TF.getText()));
        bookDesc.setId(bookDesc.getId());

        // Query to check if the book with the specified ID exists
        String checkquery = "SELECT * FROM books WHERE book_id = " + book.getBookId() + ";";
        try {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(checkquery);
            while (resultSet.next()) {
                // If the book exists, set flag to true and retrieve book details
                flag = true;
                Book book2 = new Book(
                        resultSet.getInt("book_id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getString("author"),
                        resultSet.getInt("copies")
                );

                // Set the book name and description
                bookDesc.setName(book2.getBookName());
                bookDesc.setDesc(TA.getText());

                // Insert the book description into the book_desc table
                String query = "INSERT INTO book_desc (book_id, name, book_description) VALUES (?,?,?);";
                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    preparedStatement.setInt(1, book2.getBookId());
                    preparedStatement.setString(2, book2.getBookName());
                    preparedStatement.setString(3, TA.getText());
                    preparedStatement.executeUpdate();

                    // Display success message and clear text fields
                    new ShowMessageDialog("Done", "Description Added Successfully to book "+ book2.getBookName());
                    TF.setText("");
                    TA.setText("");
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            // If the book doesn't exist, display an error message
            if (flag == false) {
                new ShowMessageDialog("Error", "No such ID");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    // Method to handle the event when the "Back" button is clicked
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        // Switch back to the homepage view
        new SwitchView("Homepage.fxml", event);
    }
}
