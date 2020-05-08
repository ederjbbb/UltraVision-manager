package controllers;

import classManager.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ViewFactory;

public class InformationTagController  extends  MainController{
    public InformationTagController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        super(validations, viewFactory, fxmlName);

    }
    public InformationTagController(){

    }

    @FXML
    private AnchorPane tagLable;

    @FXML
    private Label tagLabel;

    @FXML
    private Button closeButton;

    @FXML
    void closeTagOnclick(ActionEvent event) {
        Stage stage = (Stage)tagLabel.getScene().getWindow();
        viewFactory.closeStage(stage);
    }



 void closeTag(){
    // this tag is activated in the customerList controller when mouse enter
     Stage stage = (Stage)tagLabel.getScene().getWindow();
     viewFactory.closeStage(stage);




}
}
