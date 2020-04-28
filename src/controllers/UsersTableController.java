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
import models.User;
import models.UserData;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersTableController extends MainController implements Initializable {
        private String query;
        User user;
        MainWindowController mc = new MainWindowController();

        private Connection conn = null;
        private Connection connection = new Connection();
        private ObservableList list = FXCollections.observableArrayList();


    public UsersTableController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);


    }
    public UsersTableController (){

    }


    @FXML
    private Label timelabel;
    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lasnameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField idField;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<UserData> table;

    @FXML
    private TableColumn<UserData, String> col_name;

    @FXML
    private TableColumn<UserData, String> col_surname;

    @FXML
    private TableColumn<UserData, String> col_email;

    @FXML
    private TableColumn<UserData, Integer> col_id;

    @FXML
    private TableColumn<UserData, CheckBox> col_select;


// ALL THIS THREE METHODS TO BE REVISED IF POSSIBLE **REMINDER**  ******************************
    @FXML
        // This method is to enable editing of the cell individually
    void editEmailOnClick(TableColumn.CellEditEvent<UserData, String> userDataStringCellEditEvent) {
        UserData userData = table.getSelectionModel().getSelectedItem();
        userData.setEmail(userDataStringCellEditEvent.getNewValue());
        UserData getCellContent = table.getSelectionModel().getSelectedItem();
        String email = getCellContent.getEmail();
        int id = getCellContent.getId();
        String query = "UPDATE Users SET email = '"+email+"'WHERE id = '"+id+"' ";

        executeQueryUpdate(query);
    }

    @FXML

        // This method is to enable editing of the cell lastname on Enter key
    void editLastnameOnClick(TableColumn.CellEditEvent<UserData, String> userDataStringCellEditEvent) {
        UserData userData = table.getSelectionModel().getSelectedItem();
        userData.setLastname(userDataStringCellEditEvent.getNewValue());
        UserData getCellContent = table.getSelectionModel().getSelectedItem();
        String lastname = getCellContent.getLastname();
        String email = getCellContent.getEmail();
        String query = "UPDATE Users SET lastname = '"+lastname+"'WHERE email = '"+email+"' ";

        executeQueryUpdate(query);

    }

//******************************************************************
    @FXML
        // This method is to enable editing of the cell firstname on Enter key
    void editNameOnClick(TableColumn.CellEditEvent<UserData, String> userDataStringCellEditEvent) {
        UserData userData = table.getSelectionModel().getSelectedItem();
        userData.setFirstname(userDataStringCellEditEvent.getNewValue());
        UserData getCellContent = table.getSelectionModel().getSelectedItem();
        String firstname = getCellContent.getFirstname();
        String email = getCellContent.getEmail();
        String query = "UPDATE Users SET firstname = '"+firstname+"'WHERE email = '"+email+"' ";

       executeQueryUpdate(query);

    }


    @FXML
    void goBackOnClick() throws SQLException {
        viewFactory.showMainWindow();
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
   }

    @FXML
    void exitOnClick(){
        Stage stage = (Stage)deleteButton.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @FXML
    void addOnClick() throws SQLException {
        String addQuery = "INSERT INTO Users (firstname , lastname , email , password) values ('"+firstnameField.getText()+"','"+lasnameField.getText()+"','"+emailField.getText()+"','"+passwordField.getText()+"');";
        int row = 0;
        if(firstnameField.getText().isEmpty() || lasnameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
            viewFactory.showActionConfirmation();
        }else{
             row  = connection.updateOrDelete(addQuery);
        }

            if(row > 0){
                Stage stage = (Stage) deleteButton.getScene().getWindow();
                viewFactory.closeStage(stage);
                viewFactory.showUsersWindow();

            }else{
                viewFactory.showActionConfirmation();
            }

    }

    @FXML
    void updateOnClick(){
        updateTable();
    }

    private void updateTable() {
        String firstname = firstnameField.getText();
        String lastname = lasnameField.getText();
        String email = emailField.getText();
        String password =  passwordField.getText();
        int id = Integer.parseInt(idField.getText());

        String updateQuery = "UPDATE Users SET firstname = '"+firstname+"', lastname = '"+lastname+"', " +
                "                                       email ='"+email+"'  WHERE id = "+id+";";
        executeQueryUpdate(updateQuery);


    }
        // This method is seperate to be used in other 3 methos as all of them  are to update
    //    individual cells
    private void executeQueryUpdate(String query) {
        Connection connection = new Connection();
        try{
            connection.updateOrDelete(query);
        }catch(SQLException e){
            e.getMessage();
        }
    }
    private void currentTime() {
       mc.getCurrentTime(timelabel);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentTime();
        try {

            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void populateTable() throws SQLException {

        UserData userDataObject = null;
        String s = "select * from Users";
        Connection connection = new Connection();
        ResultSet data ;


            String query = "SELECT * FROM Users";
            // initializing connection
            try {
                 data = connection.getConnection(query);

                // looping over the result from DB to create object of userData
                while(data.next()) {
                    String name = data.getString("firstname");
                    String lastname = data.getString("lastname");
                    String email = data.getString("email");
                    int id = data.getInt("id");



                    userDataObject = new UserData(name,lastname,email, id);
                    list.add(userDataObject);


                }

            }catch(Exception e){
                e.printStackTrace();

            }
            // se properties
                 col_name.setCellValueFactory(new PropertyValueFactory<>("Firstname"));
                 col_surname.setCellValueFactory(new PropertyValueFactory<>("Lastname"));
                 col_email.setCellValueFactory(new PropertyValueFactory<>("Email"));
                 col_id.setCellValueFactory(new PropertyValueFactory<>("Id"));


                  table.setItems(list);
                  table.setEditable(true);
                  col_name.setCellFactory(TextFieldTableCell.forTableColumn());
                  col_email.setCellFactory(TextFieldTableCell.forTableColumn());
                  col_surname.setCellFactory(TextFieldTableCell.forTableColumn());
    

        }

        // This method is to edit rows of the tale individually
        @FXML
        void deleteRow(ActionEvent event) {

            // This method will get action clicked from fxml view and delete the row selected on the view
            // then, get id and delete from data base.

            UserData userData = table.getSelectionModel().getSelectedItem();// used to delete from view only
            int rowsAffected = 0;
            int id = userData.getId();
            String query = "DELETE FROM Users WHERE id ="+id;
            try{
                rowsAffected = connection.updateOrDelete(query);
                System.out.println("row" +rowsAffected+ "id deleted = " + id);
            }catch (SQLException e){
                e.getMessage();
            }
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            if(rowsAffected > 0){

            }

        }


    // This method get string from deleteRow that gets row from table tobe deleted in the  DB
    private void deleteFromDatabase(String deleteQuery) {
        try{
            Connection connection = new Connection();
            connection.updateOrDelete(deleteQuery);
        }catch(SQLException e){
            e.getMessage();
        }
    }
}



