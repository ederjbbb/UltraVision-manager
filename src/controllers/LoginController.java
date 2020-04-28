package controllers;

import classManagers.ItemsManager;
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
        public LoginController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
                super(itemsManager, viewFactory, fxmlName);
                dataList = new ArrayList<String>();

        }
    String name22;

    public String getEmail() {
        return email;
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
                connection = new Connection();
                 // NEED TO BE VALIDATED
                //  NEED TO CHECKED
                email = textField.getText();
                String password = passwordField.getText();
                user = new User(email, password);



                data = connection.getConnection(query);// This line receives the result from method
                                                                    // in the model connection



                while(data.next()){
                    //This one will loop over the db and get all of the passwords and emails to compare
                    // with the object email and password
                        dataList.add(data.getString("email"));
                        dataList.add(data.getString("password"));
                }
                if(dataList.contains(user.getEmail()) && dataList.contains(user.getPassword())){
                        viewFactory.showMainWindow();

                }else{
                    viewFactory.showNotFound();
                    //in case there is no match , popupwindow
                }


                Stage stage = (Stage)labelborder.getScene().getWindow();
                viewFactory.closeStage(stage);
        }

    public String returnEmail() {

            return name22;

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
