package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Controller class for the login page, handling user authentication.
 */
public class LoginPageController {
    // FXML elements representing buttons and input fields on the login page
    @FXML
    private Button loginBTN, clearBTN;

    @FXML
    private TextField TF;

    @FXML
    private PasswordField PF;

    /**
     * Handles the login button click event, authenticating the user.
     */
    public void login(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
        }

        // Create an Admin object and set its name and password based on user input
        Admin admin = new Admin();
        admin.setName(TF.getText());
        admin.setPassword(PF.getText());

        // SQL query to retrieve the password for the given username
        String query = ("SELECT password FROM admin WHERE USERNAME ='" + admin.getName() + "' ;");

        try {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(query);

            if (resultSet.next()) { // 1 if username exists or 0 when there's no username with this name
                String realpswrd = resultSet.getString("PASSWORD");

                if (realpswrd.equals(admin.getPassword())) {
                    // Successful login, navigate to the homepage
                    new SwitchView("HomePage.fxml", event);
                    new ShowMessageDialog("You're In !!", "Welcome " + admin.getName() + " !");
                } else {
                    new ShowMessageDialog("Oops!", "Wrong password");
                }
            } else {
                new ShowMessageDialog("Oops!", "Wrong username or password");
                TF.setText("");
                PF.setText("");
            }
        } catch (SQLException | IOException se) {
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Clears the input fields on the login page.
     */
    public void clear(ActionEvent event) {
        TF.setText("");
        PF.setText("");
    }
}
