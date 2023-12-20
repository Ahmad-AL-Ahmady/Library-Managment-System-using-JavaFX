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

public class StaffDetailsController {
    boolean flag=false;
    @FXML
    private Button backButton;

    @FXML
    private Button fetchData;

    @FXML
    private TableColumn<Staff,Integer> id;

    @FXML
    private TableColumn<Staff,String> name;

    @FXML
    private TableColumn<Staff,String> phone;

    @FXML
    private TableView<Staff> table;
    ObservableList<Staff> StaffList = FXCollections.observableArrayList();
    @FXML
    public void backButton(ActionEvent event) throws IOException {
        new SwitchView("Homepage.fxml",event);
    }

    @FXML
    public void FetchData(ActionEvent event) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            new ShowMessageDialog("Database Connection Error","There was an error connecting to the database.");
            return;
        }
        String query = "SELECT * FROM staff;";
        try {
            if (!flag) {
                Statement stm = con.createStatement();
                ResultSet resultSet = stm.executeQuery(query);
                while (resultSet.next()) {
                    Staff staff = new Staff(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone_number")
                    );
                    id.setCellValueFactory(new PropertyValueFactory<Staff,Integer>("id"));
                    name.setCellValueFactory(new PropertyValueFactory<Staff,String>("name"));
                    phone.setCellValueFactory(new PropertyValueFactory<Staff,String>("phone"));
                    StaffList.add(staff);
                }
                resultSet.close();
                stm.close();
                flag = true;
                table.setItems(StaffList);
            } else {
                new ShowMessageDialog("Error","Data already fetched");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }  finally {
            try {
                con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

}
