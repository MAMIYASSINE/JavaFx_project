package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.idao;
import connexion.Connexion;
import model.Airline;

public class AirlineService implements idao<Airline> {

	private Connection conn;

	public AirlineService() {
		this.conn = Connexion.getConnection();
	}

	 @Override
	    public boolean create(Airline airline) {
	        String sql = "INSERT INTO airlines (name, conventionNumber) VALUES (?, ?)";
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, airline.getName());
	            pstmt.setString(2, airline.getConventionNumber());
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            System.err.println("Error inserting airline: " + e.getMessage());
	            return false;
	        }
	    }

	@Override
	public boolean delete(Airline airline) {
		String sql = "DELETE FROM airlines WHERE airlineId = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, airline.getAirlineId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error deleting airline: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean update(Airline airline) {
		String sql = "UPDATE airlines SET name = ?, conventionNumber = ? WHERE airlineId = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, airline.getName());
			pstmt.setString(2, airline.getConventionNumber());
			pstmt.setInt(3, airline.getAirlineId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error updating airline: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Airline findById(int id) {
		String sql = "SELECT * FROM airlines WHERE airlineId = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Airline(rs.getInt("airlineId"), rs.getString("name"), rs.getString("conventionNumber"));
			}
		} catch (SQLException e) {
			System.err.println("Error finding airline: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Airline> findAll() {
		List<Airline> airlines = new ArrayList<>();
		String sql = "SELECT * FROM airlines";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				airlines.add(
						new Airline(rs.getInt("airlineId"), rs.getString("name"), rs.getString("conventionNumber")));
			}
		} catch (SQLException e) {
			System.err.println("Error finding airlines: " + e.getMessage());
		}
		return airlines;
	}
}
