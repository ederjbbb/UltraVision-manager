package models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class EmailChecker {

    Connection connection;
    boolean emailExists;
    String userEmail ;
    ArrayList emails;
    String passwordToSend;
    private String emailQuery = "SELECT * FROM Users";
    


    public EmailChecker(String userEmail) {
        this.userEmail = userEmail;
        connection = new Connection();
        boolean email = false; // hold initial value for email in DB
        isEmail(userEmail);



    }

    public boolean isEmail(String userEmail) {

        emailExists = false;
        emails = new ArrayList<String>();
        try {
            ResultSet result = connection.getConnection(emailQuery);

                while (result.next()){
//                    emails.add(result.getString("email"));
                    if(result.getString("email").equals(userEmail)){
                        emailExists = true;
                        selectPassword(userEmail);

                    }

                }

        } catch (SQLException e) {
            e.getErrorCode();
        }


        return emailExists;
    }

    public void selectPassword(String userEmail) throws SQLException {
            String[] strings = new String[1];
        // This method is now to select the password , since the email has alredy been checked

        String passwordQuery = "SELECT password FROM Users WHERE email = ";
        passwordQuery += "'" + userEmail + "';";
        ResultSet result = connection.getConnection(passwordQuery);
        while(result.next()){
            strings[0] =  result.getString("password");
        }

        passwordToSend = strings[0];


    }

    //====================================================Starts Inner class ================================================================
    // after the class above check if email exists in database , this class EmailSender is to send email twith password to user's email
    public class EmailSender {
        
        final String user = "visionultra199@gmail.com"; // here isthe company email
        final String password = "redeREDE4812";//email password
        // This constructor will take no parameters , it just call the method that will take a recipient parameter(TO)
        private String receiver = null;


        public EmailSender(String receiver) throws SQLException {

            this.receiver = receiver;


        }

        public void sendMessage() {
//
            String host = "mail.gmail.com";
            String to = userEmail;//change accordingly


            //Get the session object
            Properties props = new Properties();

            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.post", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.starttls.enable", "true");


                Session session = Session.getInstance(props,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(user, password);
                            }
                        });

            


            //Compose the message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(user));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
                message.setSubject("password Ultra Vision");
                message.setText("This is your password :"+  passwordToSend);




                //send the message
                Transport.send(message);

                System.out.println("sent");





            } catch (MessagingException e) {
                e.printStackTrace();
                System.out.println("no email informed");
            }



        }


    }

}






