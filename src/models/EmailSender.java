package models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class EmailSender {
    final String user = "visionultra199@gmail.com"; // here isthe company email
    final String password = "redeREDE4812";//email password
    // This constructor will take no parameters , it just call the method that will take a recipient parameter(TO)
    private String receiver = null;
    public EmailSender(String receiver) {
        this.receiver = receiver;
        sendMessage();

    }

    private void sendMessage() {
        String host = "mail.gmail.com";


        String to = receiver;//change accordingly

        //Get the session object
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.post", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
//                props.put("mail.smtp.socketFactory.class", "javafx.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
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
            message.setText("123456");

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("nno email informed");
        }

         // this inner class is in charge of accessing the database and get password
        // it is here, because it is relevant only for the EmailSender class
        class GetPassword {

            Connection connection;

            public GetPassword(String receiver ) {

                String query = "SELECT password from Users where email = 'ederjb@icloud.com';"; // query to get password according to email informed
                connection = new Connection();

                try{
                    ResultSet passwordResult =  connection.getConnection(query);
                    System.out.println(passwordResult.getString(String.valueOf(passwordResult.next())));


                }catch (SQLException e){
                    e.getErrorCode();
                }finally {

                }




            }
        }
    }



}


