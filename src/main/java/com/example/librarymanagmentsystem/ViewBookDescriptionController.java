package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ViewBookDescriptionController {
    boolean flag = false; // Flag to track whether book description is found
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField TF;

    @FXML
    private Button backBTN, viewDesc;

    @FXML
    private Label descLabel;
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml",event);
    }

    @FXML
    public void viewDesc(ActionEvent event) throws IOException{ // View book description when the "View Description" button is pressed
        Connection con = DBConnection.getConnection();
        if (con == null) {
            new ShowMessageDialog("Database Connection Error","There was an error connecting to the database.");
        }
        BookDesc bookDesc = new BookDesc();  // Create a BookDesc object and set its ID
        bookDesc.setId(Integer.parseInt(TF.getText()));
        String checkquery ="SELECT book_description FROM book_desc WHERE book_id = "+bookDesc.getId()+";"; // SQL query to retrieve book description based on book ID
        try {
            Statement stm = con.createStatement(); // Create a statement and execute the query
            ResultSet resultSet = stm.executeQuery(checkquery);
            while (resultSet.next()) {
                flag=true; // Set the book description and update the label if there's a book with this id in this table
                bookDesc.setDesc(resultSet.getString("book_description"));
                descLabel.setText(bookDesc.getDesc());
            } if (flag == false) {
                new ShowMessageDialog("Error","No such ID or this book has no description"); // Show an error message if no description is found
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}