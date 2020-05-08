package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewFactory;

public class ActionConfirmationController extends MainController {
    public ActionConfirmationController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);
    }

    @FXML
    private Button okButton;


//test
    @FXML
    void closeOkConfirmation(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
