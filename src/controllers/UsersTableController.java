package controllers;

import classManagers.ItemsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Connection;
import models.UserData;
import view.ViewFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersTableController extends MainController implements Initializable {
        private String query;

        private Connection conn = null;
        private Connection connection = new Connection();
        private ObservableList list = FXCollections.observableArrayList();


    public UsersTableController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);

    }
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




    @FXML
    void goBackOnClick(){
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
//        String addQuery = "INSERT INTO Users ( '"+firstnameField.getText()+"') " +
//                "( '"+lasnameField.getText()+"') ( '"+emailField.getText()+"') ( '"+passwordField.getText()+"')";
//        ResultSet row  = connection.makeUpdate(addQuery);
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

        String query = "UPDATE Users SET firstname = '"+firstname+"', lastname = '"+lastname+"', " +
                "                                       email ='"+email+"'  WHERE id = "+id+";";

        Connection connection = new Connection();
        try{
            connection.updateOrDelete(query);
        }catch(SQLException e){
            e.getMessage();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
                 col_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
                 col_surname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                 col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                 col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

                 table.setItems(list);

        }

        // This method is to edit rows of the tale individually
        @FXML
        void deleteRow(ActionEvent event) {

            // This method will get action clicked from fxml view and delete the row selected on the view
            // then, get id and delete from data base.

            UserData userData = table.getSelectionModel().getSelectedItem();
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
                viewFactory.showActionConfirmation();
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



