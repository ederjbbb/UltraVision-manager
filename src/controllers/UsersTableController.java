package controllers;

import classManagers.ItemsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    public UsersTableController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);

    }


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {

            populateTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    private void populateTable() throws SQLException {

        ObservableList list = FXCollections.observableArrayList();
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

                    userDataObject = new UserData(name,lastname,email);
                    list.add(userDataObject);

                            
                }

            }catch(Exception e){
                e.printStackTrace();

            }
            // se properties
                 col_name.setCellValueFactory(new PropertyValueFactory<>("firstname"));
                 col_surname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                 col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                 table.setItems(list);


        }


    }



