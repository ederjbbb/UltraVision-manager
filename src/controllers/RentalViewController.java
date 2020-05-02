package controllers;

import classManagers.ItemsManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Connection;
import models.ItemsData;
import models.RentalDataCustomer;
import models.RentalDataTable;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RentalViewController<EventKey> extends MainController implements Initializable {

    private ObservableList dbDataListTable;// Used in the populateTable methos to get data into tableview/
    ResultSet data= null; // variable used to get data from db , used in populateTable method and in
                            // populateCustomerData
    private RentalDataCustomer rentalDataCustomer;
    String tableQuery;


    public RentalViewController(ItemsManager itemsManager, ViewFactory viewFactory, String fxmlName) {
        super(itemsManager, viewFactory, fxmlName);


    }



    @FXML
    private TextField searchField;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField loyaltyNumberField;

    @FXML
    private TextField pointsField;

    @FXML
    private TextField categoryPlanField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField pendingsField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TableView<RentalDataTable> table;

    @FXML
    private TableColumn<RentalDataTable, Integer> col_id;

    @FXML
    private TableColumn<RentalDataTable, String> col_mediaType;

    @FXML
    private TableColumn<RentalDataTable, String> col_titleName;

    @FXML
    private TableColumn<RentalDataTable, String> col_genre;

    @FXML
    private TableColumn<RentalDataTable, String> col_titleType;

    @FXML
    private TableColumn<ItemsData, Number> col_price;
    private boolean triggerTable = false; // used in getLoyaltyNumber



    private RentalDataCustomer rentalData; // Object to hold data from database to fill rentalview fileds
    String category; // used in method getLoyatyNumber , thats where populateCustomerData is calle
                    // and the object is create, then it will have access to the object, after that i use
                    // category variable on switch case to change the field titletype to the customer restriction

    @FXML
    void getLoyaltyNumber() throws SQLException {
        String query = "SELECT * FROM Customers WHERE membershipNumber = '"+loyaltyNumberField.getText()+"';";
        System.out.println(query);//testing
        populateCustomerData(loyaltyNumberField.getText(),query);// receiver loyalty number an query


    }


    // This method gets data fromDb that match  Loyalty card number
    private void populateCustomerData(String text,String query) throws SQLException {

        String firstName = null;
        String lastName = null;
        String categoryPlan = null;
        int points = 0;
        int pendings = 0;

        Connection connection = new Connection();// instantiate connection

        data = connection.getConnection(query); // call method in Connection to retreive data
        // loop over data to create objecht
        while(data.next()){
            firstName = data.getString("firstname");
            lastName = data.getString("lastname");
            categoryPlan = data.getString("category_plan");
            points = data.getInt("loyalty_points");
            pendings = data.getInt("pendings");
            category = categoryPlan;// get categoryPlan to define titletype in table


        }

        rentalDataCustomer = new RentalDataCustomer(points, pendings, firstName, lastName, categoryPlan);
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        categoryPlanField.setText(categoryPlan);
        pointsField.setText(Integer.toString(points));
        pendingsField.setText(Integer.toString(pendings));
//        triggerTable = true;// used to load table after customer number is entered

            populateTable();

    }


    @FXML
    void openRentalOnClick(ActionEvent event) {
        Stage stage = (Stage)searchField.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
    //**************************** table to be populated startd here*********************************
    private void populateTable() throws SQLException {


                                                                // used in switch case
        String type = null; // result of switch case and to set table view in the Rental table view
        dbDataListTable  = FXCollections.observableArrayList();
        Connection connection = new Connection();


        // This is to change the title_type that is gonna display to match the types the customer can
                            // rent , as per restriction
        if(category.equalsIgnoreCase("Movie")) {
            type = "Movie";
        }else if (category.equalsIgnoreCase("Box Set")) {
            type = "Box Set";
        }else if(category.equalsIgnoreCase("Music") || category.equalsIgnoreCase("Live Concerts")) {
            type = " Music or live Concert";
        }else {
            type = "Premium";

        }
        tableQuery = "select * from Titles  where title_type = '"+type+"';";


        
        // initializing connection
        try {

            data = connection.getConnection(tableQuery);


            // looping over the result from DB to retreive data
            while(data.next()) {
                int id = data.getInt("titles_id");
                String media_type = data.getString("media_type");
                String title_type = data.getString("title_type");
                String title_name = data.getString("title_name");
                String genre = data.getString("genre");
                double price = data.getDouble("price");
                int qty_available = data.getInt("qty_available");




                // Creates object, object vaiablein up in the class scope
                RentalDataTable rentalDataTable= new RentalDataTable(id, media_type, title_name,
                      genre,title_type,price);
                dbDataListTable.add(rentalDataTable);


            }

        }catch(Exception e){
            e.printStackTrace();
        }
        // set properties to each cell on the tableview
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_titleName.setCellValueFactory(new PropertyValueFactory<>("titleName"));
        col_mediaType.setCellValueFactory(new PropertyValueFactory<>("mediaType"));

        col_titleType.setCellValueFactory(new PropertyValueFactory<>("titleType"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_price.setCellValueFactory(new PropertyValueFactory<ItemsData, Number>("price"));




        table.setItems(dbDataListTable);









    }

}