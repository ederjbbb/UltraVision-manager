package models;

import javafx.beans.property.SimpleStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CustomerData {


    private SimpleStringProperty firstName;
    private  SimpleStringProperty lastName;
    private  SimpleStringProperty email;
    private  SimpleStringProperty address;
    private SimpleStringProperty membershipNumber;
    private SimpleStringProperty cardNumber;
    private String neWnumberForCustomerRegistration;




    public CustomerData(String firstName, String lastName, String email, String address, String membershipNumber, String cardNumber) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.membershipNumber = new SimpleStringProperty(membershipNumber);
        this.cardNumber = new SimpleStringProperty(cardNumber);


    }
    public CustomerData(){

    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getMembershipNumber() {
        return membershipNumber.get();
    }

    public SimpleStringProperty membershipNumberProperty() {
        return membershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        this.membershipNumber.set(membershipNumber);
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public SimpleStringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.set(cardNumber);
    }


    public String getLoyaltyCardNumber() throws SQLException {
          // getting number form LoyaltyCard class
        LoyaltyCard loyaltyCardExportMode = new LoyaltyCard();
        neWnumberForCustomerRegistration  = loyaltyCardExportMode.cardNumberGenertor();
        return neWnumberForCustomerRegistration ;
    }

    //Inner class for LoyaltyCard  , since this clas is only relevante to the customer class
    class LoyaltyCard{
        private String  loyaltyMembershipNumber;
        private int  points;



    // This method generates random numbers for each costumer registered .
        // if the number exists in database , then method is called again
        String cardNumberGenertor() throws SQLException {


            Random rnd = new Random();
            String neWnumber =  Integer.toString(10000000 +rnd.nextInt(90000000));

            String query = "SELECT * FROM Customers WHERE '" + neWnumber + "' = membershipNumber;";
            Connection conn = new Connection();

            try {
                ResultSet data = conn.getConnection(query);
                if (neWnumber != (data.getString("membershipNumber"))) {
                    return neWnumber;
                } else {
                    return null;
                }

            } catch (SQLException e) {
                e.getMessage();


            }finally {

                return neWnumber;
            }


        }
    }

}











