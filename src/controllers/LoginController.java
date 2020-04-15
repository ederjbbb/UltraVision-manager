package controllers;

import classManagers.ItemsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.ViewFactory;

public class LoginController extends MainController {


        public LoginController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
                super(itemsManager, viewFactory, fxmlName);
        }

        @FXML
        private AnchorPane Loginwindow;

        @FXML
        private Label labelWindow;

        @FXML
        private ImageView imageWindow;

        @FXML
        private Label labelborder;

        @FXML
        private PasswordField passwordField;

        @FXML
        private TextField textField;

        @FXML
        private Button loginButton;
        @FXML
        private TextField errorLabel;

        @FXML
         void loginActionExit(){

                viewFactory.showMainWindow();
                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }
        @FXML
        void loginWindowExit(){


                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }

}
