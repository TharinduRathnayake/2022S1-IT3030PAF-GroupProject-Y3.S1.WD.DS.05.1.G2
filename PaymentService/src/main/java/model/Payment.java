package model;

import java.sql.*;

public class Payment { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ceb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	
	
	
	public String insertPay(String cusID, String pType, String pAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into pay(`pID`,`cusID`,`pType`,`pAmount`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cusID);
			preparedStmt.setString(3, pType);
			preparedStmt.setDouble(4, Double.parseDouble(pAmount));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	public String readPay() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='2'><tr><th>CustomerID</th><th>Payment Type</th>" + "<th>Amount paid</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from pay";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String cusID = rs.getString("cusID");
				String pType = rs.getString("pType");
				String pAmount = Double.toString(rs.getDouble("pAmount"));
				
				// Add into the html table
				output += "<tr><td>" + cusID + "</td>";
				output += "<td>" + pType + "</td>";
				output += "<td>" + pAmount + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='Payments.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='pID' type='hidden' value='" + pID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	

	public String updatePay(String pID, String cusID, String pType, String pAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE pay SET cusID=?,pType=?,pAmount=? WHERE pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, cusID);
			preparedStmt.setString(2, pType);
			preparedStmt.setString(3, pAmount);
			preparedStmt.setInt(4, Integer.parseInt(pID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	
	
	
	public String deletePay(String pID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from pay where pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}