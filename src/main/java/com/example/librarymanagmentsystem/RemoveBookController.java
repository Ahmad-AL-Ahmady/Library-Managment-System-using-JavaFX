package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Controller class for removing a book from the library system.
 */
public class RemoveBookController {
    // FXML elements representing buttons and input fields on the remove book page
    @FXML
    private TextField TF;

    @FXML
    private Button backBTN, removeBTN;

    /**
     * Handles the back button click event, navigating back to the homepage.
     */
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml", event);
    }

    /**
     * Handles the remove book button click event, removing the book from the library system.
     */
    @FXML
    public void removeBook(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
        }

        // Create a Book object and set its book ID based on user input
        Book book = new Book();
        book.setBookId(Integer.parseInt(TF.getText()));

        // SQL query to delete the book with the specified ID
        String query = "DELETE FROM books WHERE book_id = ?;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, book.getBookId());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                new ShowMessageDialog("Done", "Book Deleted Successfully");
                TF.setText("");
            } else {
                new ShowMessageDialog("Error", "No such ID");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
