package controllers;

import classManagers.ItemsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import models.Connection;
import models.CustomerData;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerListController extends MainController implements Initializable {


    MainWindowController mc =new MainWindowController();
    UsersTableController uc = new UsersTableController();
    CustomerData userDataObject = null;


    public CustomerListController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);


    }





    @FXML
    private Label timeLable;
    @FXML
    private TextField firstnameField;


    @FXML
    private TableView<CustomerData> table;

    @FXML
    private TableColumn<CustomerData, String> col_name;

    @FXML
    private TableColumn<CustomerData, String> col_lastname;

    @FXML
    private TableColumn<CustomerData, String> col_email;

    @FXML
    private TableColumn<CustomerData, String> col_address;

    @FXML
    private TableColumn<CustomerData, String> col_membership;

    @FXML
    private TableColumn<CustomerData, String> col_creditCard;


    @FXML
    private Button goBackButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button generatorButton;
    @FXML
    private ChoiceBox<?> memberChoices;

    @FXML
    void addOnClick(ActionEvent event) {

    }

    @FXML
    void deleteOnClick(ActionEvent event) {

    }
    @FXML
    private TextField memberNumber;
    @FXML
    void generateNumberOnClick(ActionEvent event) throws SQLException {
        /*This method is to create an object of class CustomerData, that has it's inner class LoyaltyCard
        Loyalty card has a method that generates car numbers , then after outer class access the method
        this method access the same method through the outer class and returns the numbers generated randomly
        and the number has already been checked on DB by the inner class called LoyaltyCard.
        * */
       CustomerData customerData = new CustomerData();
        memberNumber.setText(customerData.getLoyaltyCardNumber());


    }
    @FXML
    void exitOnClick(ActionEvent event) {
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    @FXML
    private Label tlabel;





    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {

        viewFactory.showMainWindow();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        currentTime();
    }
        // Get time from MainWindowController(Dashboard) / / avoiding code repeat
    private void currentTime() {
       mc.getCurrentTime(timeLable);
    }



    private void populateTable() throws SQLException {
        ObservableList dbDataList = FXCollections.observableArrayList(); // Collection of data to display in the tableview
                                                                        // this list receives the object
                                                                        // then, this list will be addedto tableview
        Connection connection = new Connection();

        String query = "select * from Customers";
        ResultSet data ;




// ************************************************************************************************

        // initializing connection
        try {

            data = connection.getConnection(query);


            // looping over the result from DB to retreive data
            while(data.next()) {
                String firstname = data.getString("firstname");
                String lastname = data.getString("lastname");
                String email = data.getString("email");
                String address = data.getString("address");
                String card = data.getString("card_number");
                String membershipNumber = data.getString("membershipNumber");




                // Creates object
                CustomerData customerDataObject = new CustomerData(firstname, lastname, email, address, card, membershipNumber);
                dbDataList.add(customerDataObject);
                System.out.println(customerDataObject.getFirstName());



            }


        }catch(Exception e){
            e.printStackTrace();

        }
        // set properties to each cell on the tableview
        col_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_membership.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        col_creditCard.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));



        table.setItems(dbDataList);
        // this will set cells to be changed individualy
        table.setEditable(true);
        col_lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        col_email.setCellFactory(TextFieldTableCell.forTableColumn());
        col_address.setCellFactory(TextFieldTableCell.forTableColumn());
//        col_membership.setCellFactory(TextFieldTableCell.forTableColumn()); // this can only be changed if costumer
                                                                            // register again.
        col_creditCard.setCellFactory(TextFieldTableCell.forTableColumn());





    }










}


