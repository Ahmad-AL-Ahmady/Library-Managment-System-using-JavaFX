package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStaffController {

    // FXML elements
    @FXML
    private TextField nameTF, phoneTF;

    @FXML
    private Button addBTN, clrBTN;

    // Navigate back to the homepage
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml", event);
    }

    // Add staff to the database
    @FXML
    public void addStaff(ActionEvent event) {
        Connection con = DBConnection.getConnection();

        // Check for database connection
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
        }

        // Create a Staff object
        Staff staff = new Staff(nameTF.getText(), phoneTF.getText());

        // SQL query to insert staff into the database
        String query = "INSERT INTO staff (name, phone_number) VALUES (?,?);";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            // Set parameters in the prepared statement
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, staff.getPhone());

            // Execute the update
            preparedStatement.executeUpdate();

            // Show success message
            new ShowMessageDialog("Done", "Staff Added Successfully");

            // Clear text fields
            nameTF.setText("");
            phoneTF.setText("");

        } catch (SQLException se) {
            // Handle SQL exceptions
            se.printStackTrace();
        } finally {
            try {
                // Close the connection in the "finally" block
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
