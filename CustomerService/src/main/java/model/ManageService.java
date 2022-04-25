package model;
import java.sql.*;
public class ManageService
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/service", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertItem(String topic, String description, String date)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into servicenotification (`notificationID`,`topic`,`description`,`date`)"
 + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, topic);
 preparedStmt.setString(3, description);
 preparedStmt.setString(4, date);
 
 // execute the statement

 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readItems()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>topic</th><th>description</th>" +
 "<th>date</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from servicenotification";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String notificationID = Integer.toString(rs.getInt("notificationID"));
 String topic = rs.getString("topic");
 String description = rs.getString("description");
 String date = rs.getString("date");
 // Add into the html table
 output += "<tr><td>" + topic + "</td>";
 output += "<td>" + description + "</td>";
 output += "<td>" + date + "</td>";
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='notificationID' type='hidden' value='" + notificationID
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the items.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateItem(String notificationID, String topic, String description, String date)
 
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for updating."; }
 // create a prepared statement
 String query = "UPDATE servicenotification SET topic=?,description=?,date=? WHERE notificationID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, topic);
 preparedStmt.setString(2, description);
 preparedStmt.setString(3, date);

 preparedStmt.setInt(5, Integer.parseInt(notificationID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Updated successfully";
 }
 catch (Exception e)
 {
 output = "Error while updating the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String deleteItem(String notificationID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from items where itemID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(notificationID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 output = "Deleted successfully";
 }
 catch (Exception e)
 {
 output = "Error while deleting the item.";
 System.err.println(e.getMessage());
 }
 return output;
 }
}

