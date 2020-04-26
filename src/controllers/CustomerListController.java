package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import view.ViewFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerListController extends MainController implements Initializable {
    public CustomerListController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);
    }

    @FXML
    private TableColumn<?, ?> con_name;

    @FXML
    private TableColumn<?, ?> col_lastname;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_membership;

    @FXML
    private Button goBackButton;

    @FXML
    private Button deleteButton;

    @FXML
    void addOnClick(ActionEvent event) {

    }

    @FXML
    void deleteOnClick(ActionEvent event) {

    }
    @FXML
    void exitOnClick(ActionEvent event) {
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void goBackOnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
