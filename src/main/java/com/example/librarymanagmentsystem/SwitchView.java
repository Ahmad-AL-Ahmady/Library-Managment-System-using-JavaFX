package com.example.librarymanagmentsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * A utility class for switching between JavaFX views to avoid redundancy and apply polymorphism.
 * Constructs a SwitchView object to switch to the specified FXML view.
 */
public class SwitchView {
    private Stage stage;
    private Scene scene;

    public SwitchView(String s, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(s));  // Load the FXML file and create a Parent node
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // Get the current stage from the event source
        scene = new Scene(root); // Create a new Scene using the loaded FXML root
        stage.setScene(scene);  // Set the new Scene for the stage
        stage.show();          // Show the updated stage with the new Scene
    }
}
