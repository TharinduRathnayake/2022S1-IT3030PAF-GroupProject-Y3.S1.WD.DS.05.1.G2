package model;
import java.sql.*;

public class UserProfile {

	//create the DB connection
		// TODO Auto-generated constructor stub
		
		public Connection connect() {
			Connection con=null;
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				 
				 //Provide the correct details: DBServer/DBName, username, password 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return con;
		
	}
		
		public String insertUser(String CustomerId,String CustomerfName,String CustomerlName,String AccountNumber,String CustomerNIC,String CustomerEmail,String CustomerPhone) {
			String output = "";
			
			try {
				Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for inserting."; }
				 
				// create a prepared statement
				 String query = " insert into user (`CustomerId`,`CustomerfName`,`CustomerlName`,`AccountNumber`,`CustomerNIC`,`CustomerEmail`,`CustomerPhone`)"
				 + " values (?, ?, ?, ?, ?,?,?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 //binding values
				 preparedStmt.setString(1, CustomerId);
				 preparedStmt.setString(2, CustomerfName);
				 preparedStmt.setString(3, CustomerlName);
				 preparedStmt.setInt(4,Integer.parseInt(AccountNumber));
				 preparedStmt.setString(5, CustomerNIC);
				 preparedStmt.setString(6, CustomerEmail);
				 preparedStmt.setString(7, CustomerPhone);
				 
				// execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Inserted successfully";
				 
			}
			catch(Exception e) {
				output = "Error while registering the users."; 
				 System.err.println(e.getMessage()); 
			}
			return output; 
			
		}
		
		
		public String readUser() {
			String output="";
			try {
				Connection con=connect();
				if(con==null) {
					return "Error While Connecting to the DataBase for reading";
				}
				// Prepare the HTML table to be displayed
				output = "<br><br><br><br><br><br> <table border='1' align='center'><tr><th>CustomerId</th><th>First Name</th>"+
						"<th>Last Name</th><th>Account Number</th><th>NIC</th>"+
						"<th>Email</th><th>Phone Number</th>" +
						"<th>Update</th><th>Remove</th></tr>";
						
				String query = "select * from user";
				Statement stmt = con.createStatement();
				ResultSet rs =stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while(rs.next()) {
					String CustomerId = rs.getString("CustomerId");
					String CustomerfName=rs.getString("CustomerfName");
					String CustomerlName=rs.getString("CustomerlName");
					String AccountNumber=Integer.toString(rs.getInt("AccountNumber"));
					String CustomerNIC=rs.getString("CustomerNIC");
					String CustomerEmail=rs.getString("CustomerEmail");
					String CustomerPhone=rs.getString("CustomerPhone");
					
					//Add into the html table
					output +="<tr><td>" +CustomerId+"</td>";
					output +="<td>" +CustomerfName+"</td>";
					output +="<td>" +CustomerlName+"</td>";
					output +="<td>" +AccountNumber+"</td>";
					output +="<td>" +CustomerNIC+"</td>";
					output +="<td>" +CustomerEmail+"</td>";
					output +="<td>" +CustomerPhone+"</td>";
					
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'"+ 
					 "class='btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='submit' value='Remove'"+ 
					" class='btn btn-danger'>"
					// + "<input name='itemID' type='hidden' value='" + itemID "
					// + "'>" + "</form>"
					 		+ "</td></tr>"; 
				}
				con.close();
				//complete the html page
				output+="</table>";
			}
			catch(Exception e) {
				output = "Error while reading the user details."; 
				 System.err.println(e.getMessage()); 
			}
			return output;
		

}
		public String UpdateUser(String CustomerId,String CustomerfName,String CustomerlName,String AccountNumber,String CustomerNIC,String CustomerEmail,String CustomerPhone) {
			
			String output="";
			try {
				Connection con=connect();
				if(con==null) {
					return "Error while connecting to the database for updating";
					
				}
				//create a prepared statement
				String query="UPDATE user SET CustomerfName=?,CustomerlName=?,AccountNumber=?,CustomerNIC=?,CustomerEmail=?,CustomerPhone=? WHERE CustomerId=?";
				
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 //binding values
				
				 preparedStmt.setString(1, CustomerfName);
				 preparedStmt.setString(2, CustomerlName);
				 preparedStmt.setInt(3,Integer.parseInt(AccountNumber));
				 preparedStmt.setString(4, CustomerNIC);
				 preparedStmt.setString(5, CustomerEmail);
				 preparedStmt.setString(6, CustomerPhone);
				 preparedStmt.setString(7, CustomerId);
				 
				// execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Updated successfully";
				 
			}
			catch(Exception e) {
				output = "Error while updating the item."; 
				 System.err.println(e.getMessage()); 
			}
			return output;
		}
		public String deleteUser(String CustomerId) {
			String output = "";
			try {
				Connection con = connect(); 
				 if (con == null) 
				 {return "Error while connecting to the database for deleting."; } 
				 
				// create a prepared statement
				 String query = "delete from user where CustomerId=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(CustomerId)); 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Deleted successfully"; 
			}
			catch (Exception e) 
			 { 
			 output = "Error while deleting the user details."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output;
		}
		//read a specific user details
		public String readSpecificUser(String CustomerId) {
			String output="";
			try {
				Connection con=connect();
				if(con==null) {
					return "Error While Connecting to the DataBase for reading";
				}
				// Prepare the HTML table to be displayed
				output = "<br><br><br><br><br><br> <table border='1' align='center'><tr><th>CustomerId</th><th>First Name</th>"+
						"<th>Last Name</th><th>Account Number</th><th>NIC</th>"+
						"<th>Email</th><th>Phone Number</th>" +
						"<th>Update</th><th>Remove</th></tr>";
						
				String query = "select * from user where CustomerId='22324'";
				Statement stmt = con.createStatement();
				ResultSet rs =stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while(rs.next()) {
					//String CustomerId = rs.getString("CustomerId");
					String CustomerfName=rs.getString("CustomerfName");
					String CustomerlName=rs.getString("CustomerlName");
					String AccountNumber=Integer.toString(rs.getInt("AccountNumber"));
					String CustomerNIC=rs.getString("CustomerNIC");
					String CustomerEmail=rs.getString("CustomerEmail");
					String CustomerPhone=rs.getString("CustomerPhone");
					
					//Add into the html table
					output +="<tr><td>" +CustomerId+"</td>";
					output +="<td>" +CustomerfName+"</td>";
					output +="<td>" +CustomerlName+"</td>";
					output +="<td>" +AccountNumber+"</td>";
					output +="<td>" +CustomerNIC+"</td>";
					output +="<td>" +CustomerEmail+"</td>";
					output +="<td>" +CustomerPhone+"</td>";
					
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'"+ 
					 "class='btn btn-secondary'></td>"
					 + "<td><input name='btnRemove' type='submit' value='Remove'"+ 
					" class='btn btn-danger'>"
					// + "<input name='itemID' type='hidden' value='" + itemID "
					// + "'>" + "</form>"
					 		+ "</td></tr>"; 
				}
				con.close();
				//complete the html page
				output+="</table>";
			}
			catch(Exception e) {
				output = "Error while reading the user details."; 
				 System.err.println(e.getMessage()); 
			}
			return output;
		}
		
		
}