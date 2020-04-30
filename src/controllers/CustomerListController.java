package controllers;

import classManagers.ItemsManager;
import enums.MemberCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Connection;
import models.CustomerData;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerListController extends MainController implements Initializable {

    ObservableList dbDataListTable = FXCollections.observableArrayList();

    private ResultSet data ; // used in method populateTable
    MainWindowController mc = new MainWindowController();
    UsersTableController uc = new UsersTableController();
    CustomerData userDataObject = null;
    MemberCategories categories[] = MemberCategories.values();
    private String addQuery; // This query variable is for use fo the method addOnClick


    // this tlis is to get a list of enums for populating the field of categories
    private ObservableList categories1List = FXCollections.observableArrayList(categories[0].getValue(), categories[1].getValue(), categories[2].getValue(), categories[3].getValue());
    private CustomerData customerDataObject; // used to create the object , get data and insert in table
                                            // used as well for searching in method searchOnClick
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
    private TextField searchField;

    @FXML
    private ChoiceBox<?> memberChoices;

    @FXML
    private Label tlabel;

    @FXML
    private Button generatorButton;


    @FXML
    private TableView<CustomerData> table;
    @FXML
    private TableColumn<CustomerData, Integer> col_id;

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



   //******************************Seach method starts*****************************************************************



    @FXML
     private void searchOnKeyTyped(KeyEvent event ) throws SQLException {



            FilteredList<CustomerData> filter = new FilteredList<>(dbDataListTable, p -> true);
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(customer -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String typedText = newValue.toLowerCase();
                    if (customer.getFirstName().toLowerCase().indexOf(typedText) != -1) {

                        return true;
                    }
                    if (customer.getLastName().toLowerCase().indexOf(typedText) != -1) {

                        return true;
                    }
                    if (customer.getEmail().toLowerCase().indexOf(typedText) != -1) {
                        return true;
                    }
                    if (customer.getAddress().toLowerCase().indexOf(typedText) != -1){
                        return  true;
                    }
                    if (customer.getMembershipNumber().toLowerCase().indexOf(typedText) != -1){
                        return  true;
                    }
                    if (customer.getCardNumber().toLowerCase().indexOf(typedText) != -1){
                        return  true;
                    }


                    return false;
                });
                SortedList<CustomerData> sortedList = new SortedList<>(filter);
                sortedList.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sortedList);


            });



    }


//***********************************Search method finishes************************************************************



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
    private void closeWindow(){
        // this method is to close windows, wnhen they are closed or changed
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);

    }



    @FXML
    void addOnClick() throws SQLException {
        // This query variable is declared up  in the class scope, in order to make this method reusable in
        // other Controllers
        addQuery = "INSERT INTO Customers (firstname, lastname, email, address, card_number, membershipNumber, category_plan) "
                + "values ('" + firstnameField.getText() + "','" + lastnameField.getText() + "','" + emailField.getText() + "','"
                + addressField.getText() + "', '" + creditCardField.getText() + "','" + memberNumberField.getText() + "' ,'" + category + "');";

        Connection connection = new Connection();
        int row = 0;
        category = (String) memberChoices.getSelectionModel().getSelectedItem();// get selected category
        if (isEmptyField()) {
            viewFactory.showActionConfirmation();
        } else {


            row = connection.updateOrDelete(addQuery);

            if (row > 0) {
                closeWindow();
                Stage stage = (Stage) deleteButton.getScene().getWindow();
                viewFactory.closeStage(stage);
                viewFactory.showCustomerList();

            } else {
                viewFactory.showActionConfirmation();
            }


        }


        //******************************************************************************************************


    }


    @FXML
    void deleteOnClick(ActionEvent event) {
        // This method is in charge of deleting row fromdata base and row from view


        Connection connection = new Connection();

        CustomerData customerData = table.getSelectionModel().getSelectedItem();// used to delete from view only
        int rowsAffected = 0; // is used to track if row was deleted in DB
        int id = customerData.getId(); // gets
        String query = "DELETE FROM Customers WHERE customer_id =" + id; // this line uses id from view for query.
        try {
            rowsAffected = connection.updateOrDelete(query); // this catches the row affected

        } catch (SQLException e) {
            e.getMessage();
        }
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());// this line updates the table view
                                                                                // by removing the selected row
        if (rowsAffected > 0) {
            // this was used for test

        }


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

        Connection connection = new Connection();

        String query = "select * from Customers";

        // initializing connection
        try {

            data = connection.getConnection(query);


            // looping over the result from DB to retreive data
            while(data.next()) {
                int id = data.getInt("customer_id");
                String firstname = data.getString("firstname");
                String lastname = data.getString("lastname");
                String email = data.getString("email");
                String address = data.getString("address");
                String card = data.getString("card_number");
                String membershipNumber = data.getString("membershipNumber");
                String category = data.getString("category_plan");







                // Creates object, object vaiablein up in the class scope
                customerDataObject = new CustomerData(id,firstname, lastname, email, address, card, membershipNumber,category);
                dbDataListTable.add(customerDataObject);




            }


        }catch(Exception e){
            e.printStackTrace();

        }
        // set properties to each cell on the tableview
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_membership.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        col_creditCard.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        col_category.setCellValueFactory(new PropertyValueFactory<>("category"));



        table.setItems(dbDataListTable);
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
