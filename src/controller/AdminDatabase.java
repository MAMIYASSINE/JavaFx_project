package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connexion.Connexion;
import model.Admin;

public class AdminDatabase {

	private Connection connection;

	public AdminDatabase() {
		this.connection = Connexion.getConnection();
	}

	public boolean create(Admin admin) {
		String query = "INSERT INTO admin(nomA, prenomA, emailA, passwordA, numTelA) VALUES(?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, admin.getNomA());
			stmt.setString(2, admin.getPrenomA());
			stmt.setString(3, admin.getEmailA());
			stmt.setString(4, admin.getPasswordA());
			stmt.setString(5, admin.getNumTelA());

			int results = stmt.executeUpdate();
			System.out.println("Insertion result: " + results);
			return results > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Admin findByEmailAndPassword(String email, String password) {
	    String query = "SELECT * FROM admin WHERE emailA = ? AND passwordA = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, email);
	        stmt.setString(2, password);
	        System.out.println("Querying admin with email: " + email + " and password: " + password); // Debugging output

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new Admin(rs.getInt("idA"), rs.getString("nomA"), rs.getString("prenomA"),
	                        rs.getString("emailA"), rs.getString("passwordA"), rs.getString("numTelA"));
	            } else {
	                System.out.println("No matching admin found."); // Debugging output
	                return null;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


}
