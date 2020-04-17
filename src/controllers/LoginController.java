package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Connection;
import models.EmailSender;
import userAndCustomer.User;
import view.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends MainController {
        EmailSender emailSender;
        User user ;
        private ArrayList dataList ;
        Connection connection;
        private String query = "SELECT email,password FROM Users;";
        public LoginController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
                super(itemsManager, viewFactory, fxmlName);
                dataList = new ArrayList<String>();
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

         void loginActionButton(ActionEvent actionEvent) throws SQLException {
                connection = new Connection();
                 // NEED TO BE VALIDATED
                //  NEED TO CHECKED
                String email = textField.getText();
                String password = passwordField.getText();
                user = new User(email, password);

                ResultSet data = connection.getConnection(query);

                while(data.next()){
                        dataList.add(data.getString("email"));
                        dataList.add(data.getString("password"));
                }
                if(dataList.contains(user.getEmail()) && dataList.contains(user.getPassword())){
                        viewFactory.showMainWindow();
                }else{

                }








                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }
        @FXML
        void loginWindowExit(){


                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }
//This methos is called when the user click on forgt password
         @FXML
        void passwordRecover(){

            String email = textField.getText();
            if(!email.equals(null)){


            }else{
                System.out.println("Inform an easdfamail");
            }

        }


}
