package view;

import classManagers.ItemsManager;
import controllers.LoginController;
import controllers.MainController;
import controllers.MainWindowController;
import controllers.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// This class acts as a kind of controller , but jus to generate, call all views , and then the controllers
// wiil be able to controll its own view.
public class ViewFactory{
    // this way we can haave access Itemsmanager class.
    ItemsManager itemsManager;
    // this Arraylist contais all active stages
    private ArrayList<Stage> activeStages ;


    public ViewFactory(ItemsManager itemsManager) {
        this.activeStages = new ArrayList<>();
        this.itemsManager = itemsManager;
    }



    public void showLoginWindow(){
//        Here the MainController with other controller that extends maincontroller
        // This way the controller can have access to the views as
        // I pass the controller to its views .

        MainController controller = new LoginController(itemsManager,this, "LoginView.fxml");
        initializeStage(controller);

    }


    public void showMainWindow(){

//        Here the MainController with other controller that extends maincontroller
        // This way the controller can have access to the views as
        // I pass the controller to its views .

        MainController controller = new MainWindowController(itemsManager,this, "DashboardView.fxml");
        initializeStage(controller);

    }
    public void showUsersWindow() {
        MainController controller = new UserController(itemsManager,this, "Users.fxml");
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