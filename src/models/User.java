package models;

public class User {
    private String name;
    private String lastname;
    private String email;
    private int Id;
    private String password;


    public User(String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.Id = Id;
        this.password = password;

    }
    public User(){

    }




    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return Id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
