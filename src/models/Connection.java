package models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connection {


   String dbServer = "ultra-vision.cdvsl6kqlerc.eu-west-1.rds.amazonaws.com";
   String user = "root";
   String password = "Pass1234!";
   String port = "3306";
   String dbName = "UltraVision";
   java.sql.Connection conn = null;
   Statement stmt = null;
   String query = "SELECT * FROM Users ";
   String jdbcUrl = "jdbc:mysql://" + dbServer + ":" +
           port + "/" + dbName + "?user=" + user + "&password=" + password;
   ResultSet result;
//  query to select user and email from data base
//     String userQuery = "SELECT * FROM Users WHERE email = '" + userLogged.getUn() + "' AND second_name = '" + userLogged.getPw() + "';";


   public Connection() {
      try {
          getConnection(query);
          getResultsArray();


      } catch (SQLException throwables) {
         throwables.printStackTrace();
      }
   }

   private void getResultsArray() throws SQLException {
      ArrayList list = new ArrayList();
//
//      }
      list.forEach(item -> System.out.println(item));
   }

   public ResultSet getConnection(String query) throws SQLException {

      try {
         // Get a connection to the database
         conn = DriverManager.getConnection(jdbcUrl);

         // Get a statement from the connection
         stmt = conn.createStatement();
         result = stmt.executeQuery(query);

         return result;

      } catch (SQLException se) {
         System.out.println("SQL Exception:");

         // Loop through the SQL Exceptions
         while (se != null) {
            System.out.println("State  : " + se.getSQLState());
            System.out.println("Message: " + se.getMessage());
            System.out.println("Error  : " + se.getErrorCode());

            se = se.getNextException();
         }

      } catch (Exception e) {
         System.out.println(e);
      }
      return result;
   }


   // this method isfor update and can be used for any other class
   public int updateOrDelete(String query) throws SQLException {
      int rows = 0;
      try {
         // Get a connection to the database
         conn = DriverManager.getConnection(jdbcUrl);

         // Get a statement from the connection
         stmt = conn.createStatement();
         rows = stmt.executeUpdate(query);

      } catch (SQLException se) {
         System.out.println("SQL Exception:");

         // Loop through the SQL Exceptions
         while (se != null) {
            System.out.println("State  : " + se.getSQLState());
            System.out.println("Message: " + se.getMessage());
            System.out.println("Error  : " + se.getErrorCode());

            se = se.getNextException();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }finally {

         stmt.close();
         conn.close();
      }


      return rows;
   }



}


