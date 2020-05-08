package view;

import classManager.Validations;
import controllers.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

// This class acts as a kind of controller , but jus to generate, call all views , and then the controllers
// wiil be able to controll its own view.
public class ViewFactory{
    // this way we can haave access Itemsmanager class.
    Validations validations;
    // this Arraylist contais all active stages
    private ArrayList<Stage> activeStages ;


    public ViewFactory(Validations validations) {
        this.activeStages = new ArrayList<>();
        this.validations = validations;
    }




    public void showLoginWindow(){
//        Here the MainController with other controller that extends maincontroller
        // This way the controller can have access to the views as
        // I pass the controller to its views .

        MainController controller = new LoginController(validations,this, "LoginView.fxml");
        initializeStage(controller);



    }


    public void showMainWindow() throws SQLException {

//        Here the MainController with other controller that extends maincontroller
        // This way the controller can have access to the views as
        // I pass the controller to its views .

        MainController controller = new MainWindowController(validations,this, "DashboardView.fxml");
        initializeStage(controller);

    }
    public void showUsersWindow() {
        MainController controller = new UsersTableController(validations,this, "UsersTable.fxml");
        initializeStage(controller);
    }
    public void showEmailAlreadyExist() {
        MainController controller = new emailInvalidController(validations,this, "email_Invalid.fxml");
        initializeStage(controller);
    }
    public void showNotFound() {
        MainController controller = new NotFoundController(validations,this, "notfound.fxml");
        initializeStage(controller);
    }
    public void showActionConfirmation() {
        MainController controller = new ActionConfirmationController(validations,this, "actionConfirmation.fxml");
        initializeStage(controller);
    }
    public void showEmailSentConfirmation() {
        MainController controller = new EmailSentController(validations,this, "emailSentConfirmation.fxml");
        initializeStage(controller);
    }

    public void showCustomerList() {
        MainController controller = new CustomerListController(validations,this, "CustomerListView.fxml");
        initializeStage(controller);

    }
    public void showStock() {
        MainController controller = new StockController(validations,this, "StockView.fxml");
        initializeStage(controller);

    }

    public void showRentalView() {
        MainController controller = new RentalViewController(validations,this, "RentalView.fxml");
        initializeStage(controller);

    }
    public void showTransactionConfirmation() throws SQLException {
        MainController controller = new ConfirmationController(validations,this, "transactionConfirmation.fxml");
        initializeStage(controller);

    }
    public void showReturn() {
        MainController controller = new ReturnController(validations,this, "returnView.fxml");
        initializeStage(controller);

    }


// this is in charge of creating stage and scenes
    //as every view has to have it.
    // I just have to call this method inside of every method in the ViewFactory
    private void initializeStage(MainController baseController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFxmlName()));
        fxmlLoader.setController(baseController);

        Parent parent;
        try{
            parent  = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        scene.getStylesheets().add("styles/myStyles.css");
        stage.show();
        activeStages.add(stage);

    }


    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStages.remove(stageToClose);
    }



}