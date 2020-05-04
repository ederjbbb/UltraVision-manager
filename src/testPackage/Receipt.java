package testPackage;

import models.CustomerData;

import java.sql.SQLException;

public class Receipt {
    private String Item;
    private double price;




        // Bill object
    public Receipt() {


    }







    public static void main(String[] args) throws SQLException {
        CustomerData customerData = new CustomerData();
        String number = customerData.getLoyaltyCardNumber();
        System.out.println(number);

    }
}
