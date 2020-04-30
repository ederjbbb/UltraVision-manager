package controllers;

import classManagers.ItemsManager;
import enums.MediaCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import models.Connection;
import models.ItemsData;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StockController extends MainController implements Initializable {
    MainWindowController mc = new MainWindowController();// This is to be able to use getCurrentTime method from MainWindowController
    // to create a list fo enums and populate dropbox/ChoiceBox
    private String addQuery; // This query variable is for use fo the method addOnClick
    private ResultSet data; // used in the populate methodto collect data from DB and build StockObject
    private String type;// Used in addOnClick method to create query ,and populateTable method to create object
    private ObservableList dbDataListTable;// Used in the populateTable methos to get data into tableview/StockView
    MediaCategories mediaType[] = MediaCategories.values(); // Getting enums to fill mediaCategories ObservableList below

    private ObservableList mediaCategories = FXCollections.observableArrayList(mediaType[0].toString(), mediaType[1].toString(), mediaType[2].toString());
    CustomerListController customerListController;// in order to reuse methods from CustomerListController
    public StockController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);

    }

    @FXML
    private TableView<ItemsData> table;

    @FXML
    private TableColumn<ItemsData, Integer> col_id;

    @FXML
    private TableColumn<ItemsData, String> col_mediaType;

    @FXML
    private TableColumn<ItemsData, String> col_tytleName;

    @FXML
    private TableColumn<ItemsData, String> col_genre;

    @FXML
    private TableColumn<ItemsData, String> col_director;

    @FXML
    private TableColumn<ItemsData, Number> col_year;

    @FXML
    private TableColumn<ItemsData, Number> col_price;

    @FXML
    private TableColumn<ItemsData, Number> col_totalQty;

    @FXML
    private TableColumn<ItemsData, Number> col_availableQty;

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
    private TextField year_field;
    private void closeWindow(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    private boolean isEmptyField(String type) {
        // This method checks fors empty fields
        // this methos receives a string type for DVD... and , type is received from drop box inside
        // the method where this method is called , as type is relevant for building the query string.
        if (type.isEmpty() || titleField.getText().isEmpty() || totalQuantityField.getText().isEmpty()
               || directorField.getText().isEmpty() || priceField.getText().isEmpty() || year_field.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void addOnClick() throws SQLException {
        //When clicking add button , this line gets the mediatype selected into a string variable type, below
        type = (String) mediaChoice.getSelectionModel().getSelectedItem();// get selected category

        addQuery = "INSERT INTO Titles (media_type, title_name, genre, director, year_of_release, price, total_quantity, qty_available) "
                + "values ('" + type + "','" + titleField.getText() + "','" + genreField.getText() + "','" + directorField.getText() + "','"
                +   Integer.parseInt(year_field.getText()) + "', '" + Double.parseDouble(priceField.getText()) + "','"
                + Integer.parseInt(totalQuantityField.getText()) + "' ,'" + Integer.parseInt(quantityField.getText()) + "');";
        Connection connection = new Connection();
        int row = 0;

        if (isEmptyField(type)) {
            viewFactory.showActionConfirmation();
        } else {

            row = connection.updateOrDelete(addQuery);

            if (row > 0) {
                closeWindow(); // here if changed in db confirmed , then close current window
                viewFactory.showStock(); // reopen window to refresh

            } else {
                viewFactory.showActionConfirmation();
            }


        }


    }
    //**************************** table to be populated startd here*********************************
    private void populateTable() throws SQLException {
        dbDataListTable  = FXCollections.observableArrayList();
        Connection connection = new Connection();

        String query = "select * from Titles";

        // initializing connection
        try {

            data = connection.getConnection(query);


            // looping over the result from DB to retreive data
            while(data.next()) {
                int id = data.getInt("titles_id");
                String media_type = data.getString("media_type");
                String title_name = data.getString("title_name");
                String genre = data.getString("genre");
                String director = data.getString("director");
                int year_of_release = data.getInt("year_of_release");
                double price = data.getDouble("price");
                int total_qty = data.getInt("total_quantity");
                int qty_available = data.getInt("qty_available");

//                String.format("%.2f", price);


                // Creates object, object vaiablein up in the class scope
                ItemsData itemsData= new ItemsData(id, title_name, genre, director,price,
                                                    year_of_release, total_qty,media_type,qty_available);
                dbDataListTable.add(itemsData);


            }

        }catch(Exception e){
            e.printStackTrace();
        }
        // set properties to each cell on the tableview
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_mediaType.setCellValueFactory(new PropertyValueFactory<>("mediaType"));
        col_tytleName.setCellValueFactory(new PropertyValueFactory<>("titleName"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_director.setCellValueFactory(new PropertyValueFactory<>("director"));
        col_year.setCellValueFactory(new PropertyValueFactory<ItemsData, Number>("year"));
        col_price.setCellValueFactory(new PropertyValueFactory<ItemsData, Number>("price"));
        col_totalQty.setCellValueFactory(new PropertyValueFactory<ItemsData, Number>("totalQuantity"));
        col_availableQty.setCellValueFactory(new PropertyValueFactory<ItemsData, Number>("qty_available"));



        table.setItems(dbDataListTable);
        // this will set cells to be changed individualy
        table.setEditable(true);
        col_tytleName.setCellFactory(TextFieldTableCell.forTableColumn());
        col_mediaType.setCellFactory(TextFieldTableCell.forTableColumn());
        col_tytleName.setCellFactory(TextFieldTableCell.forTableColumn());
        col_genre.setCellFactory(TextFieldTableCell.forTableColumn());
        col_director.setCellFactory(TextFieldTableCell.forTableColumn());
        col_price.setCellFactory(TextFieldTableCell.<ItemsData, Number>forTableColumn(new NumberStringConverter()));
        col_year.setCellFactory(TextFieldTableCell.<ItemsData, Number>forTableColumn(new NumberStringConverter()));
        col_availableQty.setCellFactory(TextFieldTableCell.<ItemsData, Number>forTableColumn(new NumberStringConverter()));
        col_totalQty.setCellFactory(TextFieldTableCell.<ItemsData, Number>forTableColumn(new NumberStringConverter()));







    }



    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void exitOnClick(ActionEvent event) {
        closeWindow();

    }

    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {
        viewFactory.showMainWindow();
        closeWindow();
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategories();
        try {
            populateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mc.getCurrentTime(timeLabel);
    }


    private void setCategories() {
        // This method get enums from enums.MemberCaategories adn create a list
        //using a observableList , it is an arraylist.

        mediaChoice.setItems(mediaCategories);
    }
}
