package classManager;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {
    private String name ;
    private String password;
    private String UserEmail;



    public Validations() {

    }

    public static boolean isWord(String name){
        return Pattern.matches("[a-zA-]+", name);

    }
    public boolean  isPassword(String password){
        this.password = password;
        if(this.password.contains(" ")){
            return  false;

        }else{
            return true;
        }
    }

    public static boolean isEmail(String email){

        String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailValidatin = Pattern.compile(emailRegex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailValidatin.matcher(email);
        return matcher.find();
    }
    public  static void warning(){
        JOptionPane.showMessageDialog(null, "Check fields and try again !!" );
    }

    public static void main(String[] args) {


        Validations validations = new Validations();
        System.out.println(validations.isEmail("ederjb@icloud.com"));
        //            JOptionPane.showMessageDialog(null, "Password should not contain  space" );
    }}



