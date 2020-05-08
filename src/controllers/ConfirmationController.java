package controllers;

import classManager.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.ViewFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConfirmationController extends MainController implements Initializable {
    @FXML
    private Button okButton;



    public ConfirmationController(Validations validations, ViewFactory viewFactory, String fxmlName) throws SQLException {
        super(validations, viewFactory, fxmlName);


    }

    @FXML
    void closeOkConfirmation(ActionEvent event) {
        Stage stage = (Stage)okButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
