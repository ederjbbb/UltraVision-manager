package view;

import classManager.Validations;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Launcher extends Application {
    @FXML
    public ImageView imageView;

    @Override
    public void start(Stage stage) throws Exception {

        ViewFactory viewFactory = new ViewFactory(new Validations());

        viewFactory.showLoginWindow();







    }
}
