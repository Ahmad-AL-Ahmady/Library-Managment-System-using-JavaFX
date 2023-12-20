package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
/**
 * Controller class for the homepage, handling user interactions and navigation.
 */
public class HomepageController {
    @FXML
    private Button BooksAvailableBTN,addAdmin,customerList,addBook,addStaff,
            issueBook,logout,removeBook,removeStaff,returnBook,staffDetails;
    @FXML
    public void BooksAvailable(ActionEvent event) throws IOException {
        new SwitchView("BooksAvailable.fxml",event);
    }
    @FXML
    public void AddStaff(ActionEvent event) throws IOException {
        new SwitchView("AddStaff.fxml",event);
    }
    @FXML
    public void RemoveStaff(ActionEvent event) throws IOException {
        new SwitchView("RemoveStaff.fxml",event);
    }
    @FXML
    public void LogOut(ActionEvent event) throws IOException{
        new SwitchView("LoginPage.fxml",event);
        new ShowMessageDialog("Good Bye!","Successfully Logged Out.");
    }
    @FXML
    public void AddBookDescription(ActionEvent event) throws IOException{
        new SwitchView("AddBookDescription.fxml",event);
    }
    @FXML
    public void ViewBookDescription(ActionEvent event) throws IOException{
        new SwitchView("ViewBookDescription.fxml",event);
    }
    @FXML
    public void AddBook(ActionEvent event) throws IOException{
        new SwitchView("AddBook.fxml",event);
    }
    @FXML
    public void RemoveBook(ActionEvent event) throws IOException{
        new SwitchView("RemoveBook.fxml",event);
    }
    public void AddAdmin(ActionEvent event) throws IOException{
        new SwitchView("AddAdmin.fxml",event);
    }
    public void StaffDetails(ActionEvent event) throws IOException{
        new SwitchView("StaffDetails.fxml",event);
    }
    public void CustomerList(ActionEvent event) throws IOException{
        new SwitchView("CustomerList.fxml",event);
    }
}
