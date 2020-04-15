package view;

import classManagers.ItemsManager;
import controllers.LoginController;
import controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
// This class acts as a kid of controller , but jus to generate, call all views , and then the controllers
// wiil be able to controll its own view.
public class ViewFactory {

    ItemsManager itemsManager;
    // this way we can haave access Itemsmanager class.

    public ViewFactory(ItemsManager itemsManager) {
        this.itemsManager = itemsManager;
    }

    public void showLoginWindow(){
        System.out.println("testing launcher login window");
//        Here is some polymorphism in action MainController with other controller that extends maincontroller
        MainController controller = new LoginController(itemsManager,this, "LoginView.fxml");
        intializeStage(controller);

        
    }
    public void showMainWindow(){
        System.out.println("Dashboard");
//        Here is some polymorphism in action MainController with other controller that extends maincontroller
        MainController controller = new DashboardController(itemsManager,this, "DashboardView.fxml");
        intializeStage(controller);

    }
//    ==============================================================================================================
    // this method is to initialize  the stage
    private void intializeStage(MainController mainController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(mainController.getFxmlName("DashboardView.fxml")));

        // this is the best way to pass the controller to the LoginWindow.fxml file , in orther tohave mre control.
        fxmlLoader.setController(mainController);
        Parent parent ;
        try{
            parent = fxmlLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add("styles/myStyles.css");
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
    }
    public void closeWindow(){
        System.exit(0);
    }
}