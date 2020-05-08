package controllers;

import classManager.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.ViewFactory;

public class ActionConfirmationController extends MainController {
    public ActionConfirmationController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        super(validations, viewFactory, fxmlName);
    }

    @FXML
    private Button okButton;
    @FXML
    private Label label;






    @FXML
    void closeConfirmation(ActionEvent event) {
        Stage stage = (Stage)label.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

}
