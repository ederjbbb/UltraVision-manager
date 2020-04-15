package controllers;

import classManagers.ItemsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import view.ViewFactory;

public class MainWindowController extends MainController {


        public MainWindowController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
                super(itemsManager, viewFactory, fxmlName);
        }


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


}


