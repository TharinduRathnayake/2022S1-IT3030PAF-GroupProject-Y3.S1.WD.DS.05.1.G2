package model; 
import java.sql.*; 
public class ElectricityAcc 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String addAccount(String accName, String accNumber, String premisesID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for adding."; } 
 // create a prepared statement
 String query = " insert into electricity_account (`accountID`,`accName`,`accNumber`,`premisesID`)"
 + " values (?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, accName); 
 preparedStmt.setString(3, accNumber); 
 preparedStmt.setString(4,premisesID);  
 // execute the statement

 preparedStmt.execute(); 
 con.close(); 
 output = "Added successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while adding the Account.";  
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String viewAccount() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for viewing."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Account Name</th><th>Account Number</th>" +
 "<th>Premises ID</th>" + 
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from electricity_account"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String accountID = Integer.toString(rs.getInt("accountID")); 
 String accName = rs.getString("accName"); 
 String accNumber = rs.getString("accNumber"); 
 String premisesID =rs.getString("premisesID"); 
 // Add into the html table
 output += "<tr><td>" + accName + "</td>"; 
 output += "<td>" + accNumber + "</td>"; 
 output += "<td>" + premisesID + "</td>"; 
  
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"
 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 + "<input name='accountID' type='hidden' value='" + accountID 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 output +="<form> <input name='can' type='submit' value='Remove'class='btn btn-danger'>"
 		+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
 		+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'></form>";
 
 } 
 catch (Exception e) 
 { 
 output = "Error while viewing the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateAccount(String accountID, String accName, String accNumber, String premisesID) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE electricity_account SET accName=?,accName=?,premisesID=? WHERE accountID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, accName); 
 preparedStmt.setString(2, accNumber); 
 preparedStmt.setString(3, premisesID); 
 preparedStmt.setInt(4, Integer.parseInt(accountID)); 
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
public String deleteAccount(String accountID) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from electricity_account where accountID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(accountID)); 
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

//view specific account
public String viewSpecificAccount(String accNumber) 
{ 
String output = ""; 
try
{ 
Connection con = connect(); 
if (con == null) 
{return "Error while connecting to the database for viewing."; } 
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Account Name</th><th>Account Number</th>" +
"<th>Premises ID</th>" + 
"<th>Update</th><th>Remove</th></tr>"; 

String query = "select * from electricity_account where accNumber='A001'"; 
Statement stmt = con.createStatement(); 
ResultSet rs = stmt.executeQuery(query); 
// iterate through the rows in the result set
while (rs.next()) 
{ 
//String accountID = Integer.toString(rs.getInt("accountID")); 
String accName = rs.getString("accName"); 
//String accNumber = rs.getString("accNumber"); 
String premisesID =rs.getString("premisesID"); 
// Add into the html table
output += "<tr><td>" + accName + "</td>"; 
output += "<td>" + accNumber + "</td>"; 
output += "<td>" + premisesID + "</td>"; 
 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
//+ "<td><form method='post' action='items.jsp'>"
+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
//+ "<input name='accountID' type='hidden' value='" + accountID 
//+ "'>" + "</form>
+"</td></tr>"; 
} 
con.close(); 
// Complete the html table
output += "</table>"; 
output +="<form> <input name='can' type='submit' value='Remove'class='btn btn-danger'>"
		+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
		+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'></form>";

} 
catch (Exception e) 
{ 
output = "Error while viewing the items."; 
System.err.println(e.getMessage()); 
} 
return output; 
} 
}

