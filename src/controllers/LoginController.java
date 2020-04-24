package controllers;

import classManagers.ItemsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Connection;
import models.EmailChecker;
import userAndCustomer.User;
import view.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends MainController {

        User user ;
        private ArrayList dataList ;
        Connection connection;
        private String query = "SELECT email,password FROM Users;";
        public LoginController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
                super(itemsManager, viewFactory, fxmlName);
                dataList = new ArrayList<String>();
        }




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
                    viewFactory.showNotFound();
                }


                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }
        @FXML
        void loginWindowExit(){
            // for the exit button , exit on click
                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }
//This methos is called when the user click on forgot password
         @FXML
        void passwordRecover() throws SQLException {


             EmailChecker emailChecker = new EmailChecker(textField.getText());

             if (emailChecker.isEmail(textField.getText())){
                 EmailChecker.EmailSender emailSender = emailChecker.new EmailSender(textField.getText());
                emailSender.sendMessage();

             }else{
                 viewFactory.showNotFound();
             }









        }



}
