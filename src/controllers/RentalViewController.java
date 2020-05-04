package controllers;

import classManagers.ItemsManager;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.*;
import view.ViewFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RentalViewController<EventKey> extends MainController implements Initializable {

    private ObservableList dbDataListTable;// Used in the populateTable methos to get data into tableview/
    ResultSet data= null; // variable used to get data from db , used in populateTable method and in
    // populateCustomerData
    private RentalDataCustomer rentalDataCustomer;
    private MainWindowController mwc = new MainWindowController(); // to be able to get the method currentTime

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
    private Label timeLable;

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
    @FXML
    void goBackOnClick(ActionEvent event) throws SQLException {
            viewFactory.showMainWindow();
            Stage stage = (Stage)logoutButton.getScene().getWindow();
            viewFactory.closeStage(stage);
    }




    @FXML
    private TableView<RentalDataBasket> basketTable;

    @FXML
    private TableColumn<RentalDataBasket, String> col_basketTitle;

    @FXML
    private TableColumn<RentalDataBasket, Integer> col_basketQuantity;

    @FXML
    private ListView  listView ;
    RentalDataBasket item;// object to fill the list and the UI// used in methods addOnClick and removeOnClick
    List list = new ArrayList<>();

    ObservableList observableListBasket = FXCollections.observableArrayList(); // this arraylist holds list of
                                     // items in the basket



        ArrayList<Double> prices = new ArrayList<>();// to hold prices
        @FXML
    private void addOnClick() {
            String title = table.getSelectionModel().getSelectedItem().getTitleName(); // get item title from table
        double price = table.getSelectionModel().getSelectedItem().getPrice();// get item price
        int quantity = 1;
          item = new RentalDataBasket(title, price, quantity);
//            observableListBasket.add( item); // add item to UI
            listView.getItems().add(item.getTitleName());
//            listView.getItems().add(item.getPrice());
            prices.add(item.getPrice()); // get price everytime user click button


        }

           @FXML
    private void removeOnClick() {
               listView.getItems().toArray();// in order to work with it better
               listView.getItems().remove(0);
               prices.remove(item.getPrice());

           }
    @FXML
    private void cancelOnClick(ActionEvent event) throws SQLException {
        // this method cancel the entire operation
        listView.getItems().clear();
        loyaltyNumberField.clear();
        getLoyaltyNumber();




    }
    //************************bill process starts here***************************************************



    @FXML
    private void finishOnClick(ActionEvent event) {

            // When clickon button finish is clicked  the bill is printed
        double bill = calculateBill();
        double discount = applyDiscount();
        int extraPoints = prices.size() * 10;
        final double finalBill = bill - discount;
        System.out.println("bill =" +finalBill);
        System.out.println("remaining point  ="+(points+extraPoints));
        System.out.println("extra points = " + extraPoints);
    }
    private  int points ; // used int the applyDiscount method
    private  double bill; // used in the methods below
    private double greatestPrice; // used in the method below
    private double  calculateBill(){
        // Calculate bill wit no discountt yet
        bill = 0.0;
        for (Object p : prices // loop through the array of prices, which is not shown in the basket
        ) {
            bill += (double) p;
        }
        return  bill;

    }

    // This method is to get the points , get the highest price and apply discount
    // base on the points that the customer has.
    // Point will remove most expensive items from the bill and charge the restof the bill on their card
    // whichis alredy stored in the Database
    private double  applyDiscount() {
        double greatestPrice = getHighestPrice();
        double discount = 0;
        points = rentalDataCustomer.getLoyaltyPoints();
        int counter = 0;

        while (counter != prices.size() && points > 100){
            discount += greatestPrice;
        counter++;
        points -= 100;
          }

        return discount;
    }

    // This methos gets the greatestprice to be used in the applyDiscount method
    private double getHighestPrice(){

             greatestPrice = 0.0 ;
            for(int i = 0; i < prices.size(); i++) {
                if (prices.get(i) > greatestPrice) {
                    greatestPrice = prices.get(i);
                }
            }
            return greatestPrice;
    }
    //***************************** bill process finishes here***************************************


    private RentalDataCustomer rentalData; // Object to hold data from database to fill rentalview fileds
    String category; // used in method getLoyatyNumber , thats where populateCustomerData is calle
    // and the object is create, then it will have access to the object, after that i use
    // category variable on switch case to change the field titletype to the customer restriction
    @FXML
    void getLoyaltyNumber2(MouseEvent event) throws SQLException {
        getLoyaltyNumber(); // another way to search loyaltyNumber
                            // by calling getloyaltyNumber that searches for Number as well
    }
    @FXML
    void getLoyaltyNumber() throws SQLException {

        String query = "SELECT * FROM Customers WHERE membershipNumber = '"+loyaltyNumberField.getText()+"';";

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

        mwc.getCurrentTime(timeLable);

    }
    //**************************** table to be populated startd here*********************************
    private void populateTable() throws SQLException {


        // used in switch case
        String type = null; // result of switch case and to set table view in the Rental table view
        dbDataListTable  = FXCollections.observableArrayList();
        Connection connection = new Connection();


        // This is to change the title_type that is gonna display to match the types the customer can
        // rent , as per restriction
        if(category.equalsIgnoreCase("Movies")) {
            type = "'Movie'";
        }else if (category.equalsIgnoreCase("Box Set")) {
            type = "'Box Set'";
        }else if(category.equalsIgnoreCase("Music") || category.equalsIgnoreCase("Live Concerts")) {
            type = "'Music ' Or title_type = 'Live Concerts'";
        }else if (category.equalsIgnoreCase("Premium")){
            type = "Premium";
            tableQuery = "SELECT * FROM Titles"; // if Premium everything need to be displayed on the tableview
        }
        if(type != "Premium" ){
            tableQuery = "select * from Titles  where title_type = "+type;

            // get one of the first choices if no Premium
        }




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
    @FXML
    void searchItemsOnKeyPressed(KeyEvent event) {
        FilteredList<RentalDataTable> filter = new FilteredList<>(dbDataListTable, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(customer -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (customer.getTitleName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }

                if (customer.getTitleType().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }
                if (customer.getMediaType().toLowerCase().indexOf(typedText) != -1){
                    return  true;
                }
                if(customer.getGenre().toLowerCase().indexOf(typedText) != -1){
                    return  true;
                }



                return false;
            });
            SortedList<RentalDataTable> sortedList = new SortedList<>(filter);
            sortedList.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedList);


        });
    }

}