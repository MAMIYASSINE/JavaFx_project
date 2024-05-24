package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.idao;
import connexion.Connexion;
import model.Hotel;

public class hotelservice implements idao<Hotel> {
    private Connection conn;

    public hotelservice() {
        this.conn = Connexion.getConnection();
    }

    @Override
    public boolean create(Hotel h) {
        String sql = "INSERT INTO hotels ( name, address, pricePerRoom) VALUES ( ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, h.getName());
            pstmt.setString(2, h.getAddress());
            pstmt.setDouble(3, h.getPricePerRoom());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting hotel: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Hotel h) {
        String sql = "DELETE FROM hotels WHERE hotelId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, h.getHotelId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting hotel: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Hotel h) {
        String sql = "UPDATE hotels SET name = ?, address = ?, pricePerRoom = ? WHERE hotelId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, h.getName());
            pstmt.setString(2, h.getAddress());
            pstmt.setDouble(3, h.getPricePerRoom());
            pstmt.setInt(4, h.getHotelId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating hotel: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Hotel findById(int id) {
        String sql = "SELECT * FROM hotels WHERE hotelId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Hotel(
                        rs.getInt("hotelId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDouble("pricePerRoom")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding hotel: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hotel> findAll() {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hotels.add(new Hotel(
                        rs.getInt("hotelId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDouble("pricePerRoom")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding hotels: " + e.getMessage());
        }
        return hotels;
    }
}