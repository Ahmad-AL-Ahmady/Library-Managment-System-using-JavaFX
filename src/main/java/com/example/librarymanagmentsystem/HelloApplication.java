package com.example.librarymanagmentsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main class that extends Application to launch the JavaFX application.
 */
public class HelloApplication extends Application {
    /**
     * The main entry point for the JavaFX application.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Initializes and starts the JavaFX application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {

            // Load the FXML file for the initial login page
            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

            // Create a new scene with the loaded FXML content
            Scene scene = new Scene(root);

            // Set the scene for the primary stage
            stage.setScene(scene);

            // Set the title of the application
            stage.setTitle("BookGuru");

            // Disable resizing of the application window
            stage.setResizable(false);

            // Set the application icon
            Image icon = new Image("library.png");
            stage.getIcons().add(icon);

            // Show the primary stage
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}