package controllers;

import classManager.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewFactory;

public class EmailSentController extends MainController{
    public EmailSentController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        super(validations, viewFactory, fxmlName);
    }
    @FXML
    private Button emailOkButton;

    @FXML
    void emailExitOnClick(ActionEvent event) {
        Stage stage = (Stage) emailOkButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
}
