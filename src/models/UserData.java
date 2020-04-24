package models;


import javafx.scene.control.CheckBox;

public class UserData {

    private final String firstname;
    private final String lastname;
    private final String email;
    private final int id;
    private CheckBox checkBox;


    public UserData(String firstname, String lastname, String email, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.id = id;
        this.checkBox = new CheckBox();


    }

    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}








