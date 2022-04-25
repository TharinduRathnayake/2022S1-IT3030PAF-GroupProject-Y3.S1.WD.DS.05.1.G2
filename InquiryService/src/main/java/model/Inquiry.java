package model; 
import java.sql.*; 

public class Inquiry 
{ //A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inqmng", "root", ""); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
 
		return con; 
	} 

	public String insertInquiry(String userID, String inquirySubject, String inquiryDate, String inquiryDesc) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			String query = " insert into inquiry (`inquiryID`,`userID`,`inquirySubject`,`inquiryDate`,`inquiryDesc`)"
					+ " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, userID); 
			preparedStmt.setString(3, inquirySubject); 
			preparedStmt.setString(4, inquiryDate); 
			preparedStmt.setString(5, inquiryDesc); 

			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	public String readInquiry() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			output = "<table border='1'><tr><th>User ID</th><th>Inquiry Subject</th>" +
					"<th>Inquiry Date</th>" + 
					"<th>Inquiry Description</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
 
			String query = "select * from inquiry"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			while (rs.next()) 
			{ 
				 String inquiryID = Integer.toString(rs.getInt("inquiryID")); 
				 String userID = rs.getString("userID"); 
				 String inquirySubject = rs.getString("inquirySubject"); 
				 String inquiryDate = rs.getString("inquiryDate"); 
				 String inquiryDesc = rs.getString("inquiryDesc"); 

				 output += "<tr><td>" + userID + "</td>"; 
				 output += "<td>" + inquirySubject + "</td>"; 
				 output += "<td>" + inquiryDate + "</td>"; 
				 output += "<td>" + inquiryDesc + "</td>"; 
 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='Inquiry.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='inquiryID' type='hidden' value='" + inquiryID 
						 + "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

	public String updateInquiry(String inquiryID, String userID, String inquirySubject, String inquiryDate, String inquiryDesc) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE inquiry SET userID=?,inquirySubject=?,inquiryDate=?,inquiryDesc=? WHERE inquiryID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, userID); 
			preparedStmt.setString(2, inquirySubject); 
			preparedStmt.setString(3, inquiryDate); 
			preparedStmt.setString(4, inquiryDesc); 
			preparedStmt.setInt(5, Integer.parseInt(inquiryID)); 
 // execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	public String deleteInquiry(String inquiryID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from inquiry where inquiryID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(inquiryID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the inquiry."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
}