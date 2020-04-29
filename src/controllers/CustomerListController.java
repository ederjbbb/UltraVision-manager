package controllers;

import classManagers.ItemsManager;
import enums.MemberCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import models.Connection;
import models.CustomerData;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerListController extends MainController implements Initializable {


    MainWindowController mc = new MainWindowController();
    UsersTableController uc = new UsersTableController();
    CustomerData userDataObject = null;
    MemberCategories categories[] = MemberCategories.values();




    // this tlis is to get a list of enums for populating the field of categories
    private ObservableList categories1List = FXCollections.observableArrayList(categories[0].getValue(), categories[1].getValue(), categories[2].getValue(), categories[3].getValue());

    public CustomerListController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);

    }


    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private Button goBackButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField memberNumberField;

    @FXML
    private TextField creditCardField;

    @FXML
    private Button exitButton;

    @FXML
    private Label timeLable;

    @FXML
    private ChoiceBox<?> memberChoices;

    @FXML
    private Label tlabel;

    @FXML
    private Button generatorButton;


    @FXML
    private TableView<CustomerData> table;

    @FXML
    private TableColumn<CustomerData, String> col_name;

    @FXML
    private TableColumn<CustomerData, String> col_lastname;
    @FXML
    private TableColumn<CustomerData, String> col_category;

    @FXML
    private TableColumn<CustomerData, String> col_email;

    @FXML
    private TableColumn<CustomerData, String> col_address;

    @FXML
    private TableColumn<CustomerData, String> col_membership;

    @FXML
    private TableColumn<CustomerData, String> col_creditCard;
    String category;


    @FXML
    void chooseMembership(DragEvent event) {

    }

    private void setCategories() {
        // This method get enums from enums.MemberCaategories adn create a list
        //using a observableList , it is an arraylist.
        memberChoices.setItems(categories1List);
    }

    private boolean isEmptyField() {
        // This method checks fors empty fields
        if (firstnameField.getText().isEmpty() || lastnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || creditCardField.getText().isEmpty() || addressField.getText().isEmpty()
                || memberNumberField.getText().isEmpty() || category.isEmpty()) {

            return true;

        } else {
            return false;
        }
    }

    private void clearFields() {
        // Method to clear all fields after use
//        firstnameField.clear();
//        lastnameField.clear();
//        emailField.clear();
//        addressField.clear();
//        creditCardField.clear();
//        memberNumberField.clear();

    }


    @FXML
    void addOnClick(ActionEvent event) throws SQLException {
        Connection connection = new Connection();
        int row = 0;
        category = (String) memberChoices.getSelectionModel().getSelectedItem();// get selected category
        if (isEmptyField()) {
            viewFactory.showActionConfirmation();
        } else {

            String addQuery = "INSERT INTO Users (firstname , lastname , email , address, card_number, membershipNumber, category_plan) "
                    + "values ('" + firstnameField.getText() + "','" + lastnameField.getText() + "','" + emailField.getText() + "','"
                    +addressField.getText() +"', '"+ creditCardField.getText()+"','"+memberNumberField.getText()+"' ,'"+category+"');";
            System.out.println(addQuery);
            row = connection.updateOrDelete(addQuery);

            if (row > 0) {
                Stage stage = (Stage) deleteButton.getScene().getWindow();
                viewFactory.closeStage(stage);
                viewFactory.showCustomerList();

            } else {
                viewFactory.showActionConfirmation();
            }

            clearFields();
        }
        //******************************************************************************************************


    }




    @FXML
    void deleteOnClick(ActionEvent event) {

    }

    @FXML
    void generateNumberOnClick(ActionEvent event) throws SQLException {
        /*This method is to create an object of class CustomerData, that has it's inner class LoyaltyCard
        Loyalty card has a method that generates car numbers , then after outer class access the method
        this method access the same method through the outer class and returns the numbers generated randomly
        and the number has already been checked on DB by the inner class called LoyaltyCard.
        * */
       CustomerData customerData = new CustomerData();
        memberNumberField.setText(customerData.getLoyaltyCardNumber());


    }
    @FXML
    void exitOnClick(ActionEvent event) {
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {

        viewFactory.showMainWindow();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategories(); // set categories in the dropdown.
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
                String category = data.getString("category_plan");







                // Creates object
                CustomerData customerDataObject = new CustomerData(firstname, lastname, email, address, card, membershipNumber,category);
                dbDataList.add(customerDataObject);




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
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));



        table.setItems(dbDataList);
        // this will set cells to be changed individualy
        table.setEditable(true);
        col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_lastname.setCellFactory(TextFieldTableCell.forTableColumn());
        col_email.setCellFactory(TextFieldTableCell.forTableColumn());
        col_address.setCellFactory(TextFieldTableCell.forTableColumn());
        col_creditCard.setCellFactory(TextFieldTableCell.forTableColumn());
        col_category.setCellFactory(TextFieldTableCell.forTableColumn());




    }










}


