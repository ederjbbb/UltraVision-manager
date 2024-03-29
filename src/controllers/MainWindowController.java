package controllers;

import classManager.Validations;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import view.ViewFactory;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainWindowController extends MainController implements Initializable {


            MainController mc;
            User userData  ;


        public MainWindowController  (Validations validations, ViewFactory viewFactory, String fxmlName) throws SQLException {
                super(validations, viewFactory, fxmlName);

        }
        public  MainWindowController(){


        }


       @FXML
        private Label timeLabel;

    @FXML
    private Label userloggedLabel;



        @FXML
        private TextField errorLabel;
        @FXML
        private ImageView photo1;

        @FXML
        private TableView<?> contentView;

        @FXML
        private Button exitButton;

        @FXML
        private Button stockButton;

        @FXML
        private Button customerButton;

        @FXML
        private Button returnButton;

        @FXML
        private Button renButton;

        @FXML
        private Button searchButton;

        @FXML
        private Button updateButton;

        @FXML
        private Button deleteBUtton;

        @FXML
        private Button addButton;

        @FXML
        private Button userButton;


        @FXML
        private Label userNameLabel;

    @FXML
    void openReturnControl(ActionEvent event) {
        viewFactory.showReturn();
        closeWindow();
    }

    @FXML
    void openStockOnClick(ActionEvent event) {
        viewFactory.showStock(); // This tab has alls categories seperately by type
        closeWindow();

    }
    private void closeWindow(){
        //This method is to close current window , resused  in other closings
        Stage stage = (Stage) returnButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }


    @FXML
    private Button openRentalButton;
    @FXML
        void exitOnClick() {
        closeWindow();


        }
        @FXML
        void closeBTN() {
        viewFactory.showLoginWindow();
        closeWindow();




        }
        @FXML
        void openUsers() {
        viewFactory.showUsersWindow();
        closeWindow();


        }
    @FXML
    void openRentalOnClick(ActionEvent event) {
        viewFactory.showRentalView();
        closeWindow();
    }
        @FXML
        void customerPanel(){
        viewFactory.showCustomerList();
        closeWindow();

        }




        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            getCurrentTime(timeLabel);

        }



    public void getCurrentTime(Label dateTime) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
//
    }



}


