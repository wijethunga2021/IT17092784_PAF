package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class delivery {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/gbcompany", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertDelivery(String name, String address, String contact, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into delivery(`deliveryId`,`deliveryName`,`deliveryAddress`,`deliveryContact`,`deliveryDate`)"
					+ "values ( ?,  ?,  ?,  ?,  ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, contact);
			preparedStmt.setString(5, date);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDeliverys = readDelivery();
			output = "{\"status\":\"success\", \"data\": \"" + newDeliverys + "\"}";
			
		} catch (Exception e) {
			
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the Delivery.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}
	
	public String readDelivery() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Delivery Name</th><th>Address</th><th>Contact</th><th>Date</th><th>Update</th><th>Remove</th></tr>"; 
			
			String query = "select * from delivery";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String deliveryId = Integer.toString(rs.getInt("deliveryId"));
				String deliveryName = rs.getString("deliveryName");
				String deliveryAddress = rs.getString("deliveryAddress");
				String deliveryContact = rs.getString("deliveryContact");
				String deliveryDate = rs.getString("deliveryDate");
				
				// Add into the html table
				
				
				
				output += "<tr><td><input id='hidDeliveryIDUpdate' name='hidDeliveryIDUpdate' type='hidden' value='"+ deliveryId + "'>" + deliveryName + "</td>";
				output += "<td>" + deliveryAddress + "</td>";
				output += "<td>" + deliveryContact + "</td>";
				output += "<td>" + deliveryDate + "</td>";
				// buttons
				
				   output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
	                        + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger' data-deliveryid='"
	                        + deliveryId + "'>" + "</td></tr>";
				
				
			}
			con.close();
			
			// Complete the html table
			output += "</table>";
		} 
		catch (Exception e) {
			output = "Error while reading the Deliverys.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateDelivery(String deliveryId, String name, String address, String contact, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE delivery SET deliveryName=?,deliveryAddress=?,deliveryContact=?,deliveryDate=? WHERE deliveryId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setString(3, contact);
			preparedStmt.setString(4, date);
			preparedStmt.setInt(5, Integer.parseInt(deliveryId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDeliverys = readDelivery();
			output = "{\"status\":\"success\", \"data\": \""+ newDeliverys +"\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Delivery.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteDelivery(String deliveryId) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from delivery where deliveryId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(deliveryId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDeliverys = readDelivery();
			output = "{\"status\":\"success\", \"data\": \"" + newDeliverys + "\"}";
		} 
		catch (Exception e) 
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Delivery.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
