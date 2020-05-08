package controllers;

import classManager.Validations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Connection;
import models.ReturnItemsAndHistory;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReturnController extends  MainController implements Initializable {
    private ResultSet result = null;
    private Connection connection = new Connection();
    private List historyList = new ArrayList<ReturnItemsAndHistory>();// used for table history
    private List returnList = new ArrayList<ReturnItemsAndHistory>();// used for table return
    private ReturnItemsAndHistory returnItems; // used in populateItems method to create object
    private ReturnItemsAndHistory returnHistory; // used in populateHistory method to create object
    private ObservableList dbDataItemsList = FXCollections.observableArrayList();// used in populateItems
    private ObservableList dbDataHistoryList = FXCollections.observableArrayList();// used in populateHistoryList
    private MainWindowController mainController = new MainWindowController();// to use getCurrenttime form MainWindowController()


    public ReturnController(Validations validations, ViewFactory viewFactory, String fxmlName) {
        super(validations, viewFactory, fxmlName);

    }

    @FXML
    private Label timeLabel;

    @FXML
    private TextField nameField;

    @FXML
    private TextField memberNumberFIeld;
    @FXML
    private TextField lastnameField;

    @FXML
    private Button returnButton;

    @FXML
    private Button searchButton;

    @FXML
    void lastnameField(ActionEvent event) {

    }
    @FXML
    private TableView<ReturnItemsAndHistory> itemsTable;


    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_itemsTilte;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_itemsPickup;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_itemsReturnDate;

    @FXML
    private TableView<ReturnItemsAndHistory> historyTable;

    @FXML
    private TableColumn<ReturnItemsAndHistory, Integer> col_historyId;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_historyTitle;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_historyPickupDate;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_historyReturnDate;

    @FXML
    private TableColumn<ReturnItemsAndHistory, String> col_historyCharges;

    @FXML
    private TableColumn<?, ?> col_historyPointsUsed;
    @FXML
    private TableColumn<?, ?> col_historyExtraPoints;

    @FXML
    private TableColumn<?, ?> col_historyPointsRemaining;

    @FXML
    private TableColumn<?, ?> col_historyReceipt;
    @FXML
    private TextField searchHistoryField;


    @FXML
    void returnItemsOnClick(ActionEvent event) {
        int row = 0;
        int id = itemsTable.getSelectionModel().getSelectedItem().getId();
        itemsTable.getItems().removeAll(itemsTable.getSelectionModel().getSelectedItem());// updade table view

        String query = "DELETE FROM Reports WHERE report_id = '"+id+"' ;";
        Connection connection = new Connection();
        try {
            row = connection.updateOrDelete(query);
        } catch (SQLException e) {
            System.out.println("no more items to delete");
            e.printStackTrace();
        }

    }




    @FXML
    private void searchMemberOnClick(ActionEvent event) throws SQLException {
        String query = "SELECT * FROM Reports WHERE member_number = '"+memberNumberFIeld.getText()+"';";
        String  titleName,  pickupDate, returnDate, receiptNumber;
        double charges,pointsUsed, pointsRemaining ;
        int extraPoints, id;



        result = connection.getConnection(query);
         while (result.next()){
             nameField.setText(result.getString("first_name"));
             lastnameField.setText(result.getString("last_name"));


//             ReturnItemsAndHistory historyObj = new ReturnItemsAndHistory();
         }
         if(result != null){
             populateItemsTable();// call to fiil up tables after , member number is found
         }


    }
    private  void closeWindow(){
        Stage stage = (Stage)returnButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void logoutOnClick(ActionEvent event) {
        viewFactory.showLoginWindow();
        closeWindow();

    }

    @FXML
    private Button goBackButon;

    @FXML
    private Label logoutLabelButton;

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            mainController.getCurrentTime(timeLabel);
    }
    private ResultSet data;
    private void populateItemsTable() {
        Connection connection = new Connection();

        String query = "select * from Reports where member_number = "+memberNumberFIeld.getText();

        // initializing connection
        try {

            data = connection.getConnection(query);


            // looping over the result from DB to retreive data
            double extrapoints= 0;
            while(data.next()) {

                int id = data.getInt("report_id");
                String name = data.getString("first_name");
                String lastname = data.getString("last_name");
                String titleName = data.getString("title_name");
                String pickupDate = data.getString("pickup_date");
                String returnDate = data.getString("return_date");
                double pointsUsed = data.getDouble("points_used");
                double pointsRemaining = data.getDouble("points_remaining");
                double amountCharged = data.getDouble("amount_charged");
                int pendings = data.getInt("pendings");
                String receiptNumber= data.getString("receipt_number");

                // Creates object, object vaiablein up in the class scope

                 returnHistory = new ReturnItemsAndHistory(id, titleName, pickupDate, returnDate,
                         amountCharged, pointsUsed, pointsRemaining, pendings, receiptNumber); // Object for History
                                                                                        // table
                returnItems = new ReturnItemsAndHistory(id,titleName, pickupDate, returnDate);


                 returnHistory.setGainedPoints(pendings);
                dbDataHistoryList.add(returnHistory);
                dbDataItemsList.add(returnItems);

            }


        }catch(Exception e){
            e.printStackTrace();

        }
        // set properties to each cell on the tableview History
        col_historyId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_historyTitle.setCellValueFactory(new PropertyValueFactory<>("titleName"));
        col_historyPickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        col_historyReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        col_historyCharges.setCellValueFactory(new PropertyValueFactory<>("charges"));
        col_historyPointsUsed.setCellValueFactory(new PropertyValueFactory<>("pointsUsed"));
        col_historyPointsRemaining.setCellValueFactory(new PropertyValueFactory<>("pointsRemaining"));
        col_historyReceipt.setCellValueFactory(new PropertyValueFactory<>("receiptNumber"));
        col_historyExtraPoints.setCellValueFactory(new PropertyValueFactory<>("gainedPoints"));

        historyTable.setItems(dbDataHistoryList);

        // set properties to each cell on the tableview History
        col_itemsTilte.setCellValueFactory(new PropertyValueFactory<>("titleName"));
        col_itemsPickup.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        col_itemsReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        itemsTable.setItems(dbDataItemsList);
    }
    @FXML
    void clearAllOnClick(){
        itemsTable.getItems().clear();
        historyTable.getItems().clear();
        nameField.clear();
        lastnameField.clear();
        memberNumberFIeld.clear();
    }

    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {
            viewFactory.showMainWindow();
       closeWindow();
    }
    @FXML
    void searchHistory(KeyEvent event) {

        FilteredList<ReturnItemsAndHistory> filter = new FilteredList<>(dbDataHistoryList, p -> true);
        searchHistoryField.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(customer -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (customer.getTitleName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (customer.getReceiptNumber().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                if (customer.getPickupDate().toLowerCase().indexOf(typedText) != -1){
                    return  true;
                }
                if(customer.getReturnDate().toLowerCase().indexOf(typedText) != -1){
                    return  true;
                }



                return false;
            });
            SortedList<ReturnItemsAndHistory> sortedList = new SortedList<>(filter);
            sortedList.comparatorProperty().bind(historyTable.comparatorProperty());
            historyTable.setItems(sortedList);


        });
    }



}
