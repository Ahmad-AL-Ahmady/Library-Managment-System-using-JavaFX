package com.example.librarymanagmentsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BooksAvailableController {
    // Flag to check if data has already been fetched
    boolean flag = false;

    // FXML elements injection
    @FXML
    ImageView imageView;

    @FXML
    TableView<Book> table = new TableView<Book>();

    @FXML
    private TableColumn<Book, String> category, author, name;

    @FXML
    private TableColumn<Book, Integer> copies, id;

    // ObservableList to hold book data for the TableView
    ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    private Button backButton, fetchData;

    // Action event for navigating back to the homepage
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml", event);
    }

    // Action event for fetching and displaying book data
    @FXML
    public void FetchData(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        // Check if a database connection is established
        if (con == null) {
            new ShowMessageDialog("Database Connection Error", "There was an error connecting to the database.");
            return;
        }

        // SQL query to select all books from the database
        String query = "SELECT * FROM books;";

        try {
            // Check if data has already been fetched
            if (!flag) {
                // Create a statement and execute the query
                Statement stm = con.createStatement();
                ResultSet resultSet = stm.executeQuery(query);

                // Loop through the result set and create Book objects
                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("book_id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getString("author"),
                            resultSet.getInt("copies")
                    );
                    // Add the book to the ObservableList
                    bookList.add(book);
                }

                // Close the result set and statement
                resultSet.close();
                stm.close();

                // Update the flag to indicate that data has been fetched
                flag = true;

                // Set cell value factories for TableView columns
                id.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookId"));
                name.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
                category.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
                author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
                copies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("copies"));

                // Set the items in the TableView to the ObservableList
                table.setItems(bookList);
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
