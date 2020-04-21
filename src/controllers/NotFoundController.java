package controllers;

import classManagers.ItemsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.ViewFactory;

public class NotFoundController  extends MainController{
    public NotFoundController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);
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
