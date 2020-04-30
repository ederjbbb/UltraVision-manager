package controllers;

import classManagers.ItemsManager;
import enums.MediaCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.ViewFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockController extends MainController implements Initializable {
    MainWindowController mc = new MainWindowController();// This is to be able to use getCurrentTime method from MainWindowController
    // to create a list fo enums and populate dropbox/ChoiceBox
    MediaCategories mediaType[] = MediaCategories.values(); // Getting enums to fill mediaCategories ObservableList below

    private ObservableList mediaCategories = FXCollections.observableArrayList(mediaType[0].toString(), mediaType[1].toString(), mediaType[2].toString());
    CustomerListController customerListController;// in order to reuse methods from CustomerListController
    public StockController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);

    }

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_lastname;

    @FXML
    private TableColumn<?, ?> col_email;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_membership;

    @FXML
    private TableColumn<?, ?> col_creditCard;

    @FXML
    private TableColumn<?, ?> col_category;

    @FXML
    private TableColumn<?, ?> col_category1;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField priceField;

    @FXML
    private Button additemButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField totalQuantityField;

    @FXML
    private TextField creditCardField;

    @FXML
    private Button exitButton;

    @FXML
    private TextField searchField;

    @FXML
    private Label timeLabel;

    @FXML
    private Label tlabel;
    @FXML
    private ChoiceBox<?> mediaChoice;

    @FXML
    private TextField directorField;

    @FXML
    private TextField quantityField;

    @FXML
    void addOnClick() {
        //When clicking add button , this line gets the mediatype selected into a string variable type, below
        String type = (String) mediaChoice.getSelectionModel().getSelectedItem();// get selected category

    }

    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void exitOnClick(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {
        viewFactory.showMainWindow();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategories();
        mc.getCurrentTime(timeLabel);




    }
    //************************************************************************************************
    private void setCategories() {
        // This method get enums from enums.MemberCaategories adn create a list
        //using a observableList , it is an arraylist.

        mediaChoice.setItems(mediaCategories);
    }
}
