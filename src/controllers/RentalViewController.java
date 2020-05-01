package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.ViewFactory;

public class RentalViewController  extends MainController{
    public RentalViewController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);


    }


    @FXML
    private TextField searchField;

    @FXML
    private Button logoutButton;

    @FXML
    void openRentalOnClick(ActionEvent event) {
        Stage stage = (Stage)searchField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
