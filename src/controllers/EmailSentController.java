package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewFactory;

public class EmailSentController extends MainController{
    public EmailSentController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);
    }
    @FXML
    private Button emailOkButton;

    @FXML
    void emailExitOnClick(ActionEvent event) {
        Stage stage = (Stage) emailOkButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
