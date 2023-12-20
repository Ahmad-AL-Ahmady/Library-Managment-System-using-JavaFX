package com.example.librarymanagmentsystem;

import javafx.scene.control.Alert;
/**
 * A simple utility class for displaying information dialog boxes to avoid redundancy and apply polymorphism
 */
public class ShowMessageDialog {
    public ShowMessageDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an instance of the Alert class with INFORMATION type
        alert.setTitle(title); // Set the title for the information dialog
        alert.setHeaderText(null); // Ensure that the header text is null (no header in All cases)
        alert.setContentText(message); // Set the content text (wanted message) for the information dialog
        alert.showAndWait(); // Show the information dialog and wait for user interaction
    }
}
