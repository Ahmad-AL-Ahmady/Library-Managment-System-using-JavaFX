package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAdminController {
    private Stage stage;
    private Scene scene;
    // FXML annotations for the UI elements
    @FXML
    private TextField nameTF,phoneTF,passTF;
    @FXML
    private Button addBTN,backBTN;
    @FXML
    public void backButton(ActionEvent event) throws IOException { // Switch to the homepage when the back button is pressed
        new SwitchView("Homepage.fxml",event);
    }
    @FXML
    public void addAdmin(ActionEvent event){ // Add a new admin when the "Add" button is pressed
        Connection con = DBConnection.getConnection(); // Establish a database connection using built database class
        if (con == null) {
            // Show an error message if the connection fails
            new ShowMessageDialog("Database Connection Error","There was an error connecting to the database.");
        }
        Admin admin = new Admin(nameTF.getText(),passTF.getText(),phoneTF.getText()); // Create an Admin object with values from text fields
        String query = "INSERT INTO admin (username, password, telephone) VALUES (?,?,?);";  // SQL query to insert a new admin into the database
        try (PreparedStatement preparedStatement = con.prepareStatement(query)){
            // Set parameters for the prepared statement
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getPhone());
            preparedStatement.executeUpdate(); // Execute the update query
            new ShowMessageDialog("Done","Admin Added Successfully");   // Show a success message and Clear text fields after adding admin
            nameTF.setText("");
            passTF.setText("");
            phoneTF.setText("");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

