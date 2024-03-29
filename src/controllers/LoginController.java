package controllers;

import classManager.Validations;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Connection;
import models.EmailChecker;
import models.User;
import view.ViewFactory;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController extends MainController  {

        User user ;
        private ArrayList dataList ;
        Connection connection;
        ResultSet data;
        private String email;
        private String query = "SELECT email,password FROM Users;";
        public LoginController(Validations validations, ViewFactory viewFactory, String fxmlName) {
                super(validations, viewFactory, fxmlName);
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
         void loginActionButton() throws SQLException {

            if (!Validations.isEmail(textField.getText())) {
                viewFactory.showNotFound();
            } else {


                connection = new Connection();
                // NEED TO BE VALIDATED
                //  NEED TO CHECKED
                email = textField.getText();
                String password = passwordField.getText();
                user = new User(email, password);


                data = connection.getConnection(query);// This line receives the result from method
                // in the model connection


                while (data.next()) {
                    //This one will loop over the db and get all of the passwords and emails to compare
                    // with the object email and password
                    dataList.add(data.getString("email"));
                    dataList.add(data.getString("password"));
                }
                if (dataList.contains(user.getEmail()) && dataList.contains(user.getPassword())) {
                    viewFactory.showMainWindow();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    viewFactory.closeStage(stage);


                } else {
                    viewFactory.showNotFound();
                    //in case there is no match , popupwindow
                }
            }
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
                  // if emailSender execute it means email is in the DB and email sent confirmation will be called
                 // otherwise email not found will b called
              viewFactory.showEmailSentConfirmation();

             }else{
                 viewFactory.showNotFound();
             }


        }

}
