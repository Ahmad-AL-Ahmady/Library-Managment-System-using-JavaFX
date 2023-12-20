package com.example.librarymanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerListController {
    // Flag to check if data has already been fetched
    boolean flag = false;

    // FXML elements injection
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Customer, String> email;

    @FXML
    private Button fetchData;

    @FXML
    private TableColumn<Customer, Integer> id;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableView<Customer> table;

    // ObservableList to hold customer data for the TableView
    ObservableList<Customer> customers = FXCollections.observableArrayList();

    // Action event for navigating back to the homepage
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml", event);
    }

    // Action event for fetching and displaying customer data
    @FXML
    public void FetchData(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        // Check if a database connection is established
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
            return;
        }

        // SQL query to select all customers from the database
        String query = "SELECT * FROM customers;";

        try {
            // Check if data has already been fetched
            if (!flag) {
                // Create a statement and execute the query
                Statement stm = con.createStatement();
                ResultSet resultSet = stm.executeQuery(query);

                // Loop through the result set and create Customer objects
                while (resultSet.next()) {
                    Customer customer = new Customer(
                            resultSet.getInt("customer_id"),
                            resultSet.getString("customer_name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number")
                    );
                    // Add the customer to the ObservableList
                    customers.add(customer);
                }

                // Close the result set and statement
                resultSet.close();
                stm.close();

                // Set cell value factories for TableView columns
                id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerId"));
                name.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
                email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
                phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNumber"));

                // Set the items in the TableView to the ObservableList
                table.setItems(customers);

                // Update the flag to indicate that data has been fetched
                flag = true;
            } else {
                // Display an error message if data has already been fetched
                new ShowMessageDialog("Error", "Data already fetched");
            }
        } catch (SQLException se) {
            // Print stack trace in case of a SQL exception
            se.printStackTrace();
        } finally {
            try {
                // Close the database connection
                con.close();
            } catch (SQLException se) {
                // Print stack trace in case of a SQL exception during closing
                se.printStackTrace();
            }
        }
    }
}
