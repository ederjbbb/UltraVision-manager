package models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerData {



    private SimpleStringProperty firstName;
    private  SimpleStringProperty lastName;
    private  SimpleStringProperty email;
    private  SimpleStringProperty address;
    private SimpleIntegerProperty membershipNumber;
    private SimpleIntegerProperty cardNumber;

    public CustomerData(SimpleStringProperty firstName,
                        SimpleStringProperty lastName, SimpleStringProperty email,
                        SimpleStringProperty address, SimpleIntegerProperty membershipNumber,
                        SimpleIntegerProperty cardNumber) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.membershipNumber = membershipNumber;
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName.get();
    }

  

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }




    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }



    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getAddress() {
        return address.get();
    }



    public void setAddress(String address) {
        this.address.set(address);
    }

    public int getMembershipNumber() {
        return membershipNumber.get();
    }



    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber.set(membershipNumber);
    }

    public int getCardNumber() {
        return cardNumber.get();
    }



    public void setCardNumber(int cardNumber) {
        this.cardNumber.set(cardNumber);
    }
}

