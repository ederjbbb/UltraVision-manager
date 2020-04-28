package models;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserData extends User{

    private SimpleStringProperty firstname;
    private  SimpleStringProperty lastname;
    private  SimpleStringProperty email;
    private SimpleIntegerProperty id;



    public UserData(String firstname, String lastname, String email, int id) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.id = new SimpleIntegerProperty(id);



    }
    public UserData(){

    }




    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }
    public void setLastname(String lastname){
        this.lastname = new SimpleStringProperty(lastname);
    }
    public void setEmail(String email){
        this.email = new SimpleStringProperty(email);
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getEmail() {
        return email.get();
    }

    public int getId() {
        return id.get();
    }


}








