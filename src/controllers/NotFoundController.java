package controllers;

import classManager.Validations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.ViewFactory;

public class NotFoundController  extends MainController{
    public NotFoundController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        super(validations, viewFactory, fxmlName);
    }
    @FXML
    private Button exitButon;

    @FXML
    private Label error;


@FXML
void exit(){
    Stage stage2 = (Stage) error.getScene().getWindow();
    viewFactory.closeStage(stage2);
}
}
