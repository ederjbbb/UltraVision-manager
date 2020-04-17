package controllers;

import classManagers.ItemsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Connection;
import view.ViewFactory;

import java.sql.SQLException;

public class UsersTableController extends MainController{

    public UsersTableController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);
    }

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;


    @FXML
    void goBackOnClick(){
        viewFactory.showMainWindow();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);

   }

    @FXML
    void exitOnClick(){
        String query = " SELECT email, password from Users;";
        Connection connection = new Connection();
        try {
            connection.getConnection(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);

    }




}
