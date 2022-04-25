package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Power {
	
	 //A common method to connect to the DB
		private Connection connect() 
		{ 
			Connection con = null; 
			
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
	 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ceb", "root", ""); 
			} 
			catch (Exception e) 
			{e.printStackTrace();} 
	 
			return con; 
		} 

		public String insertPower(String name, String zone, String tperiod) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
				String query = " insert into power (`scheduleID`,`locationName`,`relatedZone`,`timePeriod`)"
						+ " values (?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 

				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, zone); 
				preparedStmt.setString(4, tperiod); 
				

				preparedStmt.execute(); 
				con.close(); 
				output = "Inserted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while inserting the power."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 

		public String readPower() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for reading."; } 
				output = "<table border='1'><tr><th>Location Name</th><th>Related Zone</th>" +
						"<th>Time Period</th>" + 
						"<th>Update</th><th>Remove</th></tr>"; 
	 
				String query = "select * from power"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 

				while (rs.next()) 
				{ 
					 String scheduleID = Integer.toString(rs.getInt("scheduleID")); 
					 String locationName = rs.getString("locationName"); 
					 String relatedZone = rs.getString("relatedZone"); 
					 String timePeriod = rs.getString("timePeriod"); 

					 output += "<tr><td>" +locationName + "</td>"; 
					 output += "<td>" + relatedZone + "</td>"; 
					 output += "<td>" + timePeriod + "</td>"; 
	 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='Power.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							 + "<input name='scheduleID' type='hidden' value='" + scheduleID 
							 + "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
				output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the power."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 

		public String updatePower(String ID, String name , String zone, String tperiod) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for updating."; } 
				// create a prepared statement
				String query = "UPDATE power SET locationName=?,relatedZone=?,timePeriod=? WHERE scheduleID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, name); 
				preparedStmt.setString(2, zone); 
				preparedStmt.setString(3, tperiod); 
				preparedStmt.setInt(4, Integer.parseInt(ID)); 
	 // execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Updated successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while updating the power."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		
		public String deletePower(String scheduleID) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for deleting."; } 
				// create a prepared statement
				String query = "delete from power where scheduleID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(scheduleID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				output = "Deleted successfully"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while deleting the power."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}


}
