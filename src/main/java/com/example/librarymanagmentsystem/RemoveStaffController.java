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
 * Controller class for handling staff removal in the library management system.
 */
public class RemoveStaffController {
    // FXML elements for UI interaction
    @FXML
    private TextField TF; // Text field for entering the staff ID

    @FXML
    private Button backBTN, removeBTN; // Buttons for navigation and staff removal

    /**
     * Handles the back button click event, navigating back to the homepage.
     */
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml", event); // Navigate back to the homepage
    }

    /**
     * Handles the remove staff button click event, removing the staff member from the system.
     */
    @FXML
    public void removeStaff(ActionEvent event) {
        Connection con = DBConnection.getConnection(); // Get a database connection
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
        }

        // Create a Staff object and set its ID based on user input
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(TF.getText()));

        // SQL query to delete the staff member with the specified ID
        String query = "DELETE FROM staff WHERE id = ?;";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, staff.getId()); // Set staff ID in the SQL query
            int rows = preparedStatement.executeUpdate(); // Execute the update query

            if (rows > 0) {
                new ShowMessageDialog("Done", "Staff Deleted Successfully");
                TF.setText(""); // Clear the text field after successful deletion
            } else {
                new ShowMessageDialog("Error", "No such ID");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close(); // Close the database connection
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
