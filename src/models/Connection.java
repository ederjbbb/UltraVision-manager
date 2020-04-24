package models;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connection {


   private String dbServer = "ultra-vision.cdvsl6kqlerc.eu-west-1.rds.amazonaws.com";
   private String user = "root";
   private String password = "Pass1234!";
   private String port = "3306";
   private String dbName = "UltraVision";

   private java.sql.Connection conn = null;
   private Statement stmt = null;
   private String query = "SELECT * FROM Users ";
   private String jdbcUrl = "jdbc:mysql://" + dbServer + ":" +
           port + "/" + dbName + "?user=" + user + "&password=" + password;
   private ResultSet result;
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
      }finally {


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
      }
      // Connection will be closed in each controller where it is invoked;
      return rows;
   }

}


