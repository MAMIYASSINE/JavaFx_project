package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connexion.Connexion;
import model.Voyage;
import application.idao;

public class VoyageService implements idao<Voyage> {
    private Connection conn;

    public VoyageService() {
        this.conn = Connexion.getConnection();
    }

    @Override
    public boolean create(Voyage v) {
        String sql = "INSERT INTO voyages (travelDate, destination, ticketPrice, conventionNumber, stayType, stayDuration, airlineId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(v.getTravelDate().getTime()));
            pstmt.setString(2, v.getDestination());
            pstmt.setDouble(3, v.getTicketPrice());
            pstmt.setString(4, v.getConventionNumber());
            pstmt.setString(5, v.getStayType());
            pstmt.setInt(6, v.getStayDuration());
            pstmt.setInt(7, v.getAirlineId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting voyage: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Voyage v) {
        String sql = "DELETE FROM voyages WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, v.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting voyage: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Voyage v) {
        String sql = "UPDATE voyages SET travelDate = ?, destination = ?, ticketPrice = ?, conventionNumber = ?, stayType = ?, stayDuration = ?, airlineId = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, new java.sql.Date(v.getTravelDate().getTime()));
            pstmt.setString(2, v.getDestination());
            pstmt.setDouble(3, v.getTicketPrice());
            pstmt.setString(4, v.getConventionNumber());
            pstmt.setString(5, v.getStayType());
            pstmt.setInt(6, v.getStayDuration());
            pstmt.setInt(7, v.getAirlineId());
            pstmt.setInt(8, v.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating voyage: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Voyage findById(int id) {
        String sql = "SELECT * FROM voyages WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Voyage(
                        rs.getInt("id"),
                        rs.getDate("travelDate"),
                        rs.getString("destination"),
                        rs.getDouble("ticketPrice"),
                        rs.getString("conventionNumber"),
                        rs.getString("stayType"),
                        rs.getInt("stayDuration"),
                        rs.getInt("airlineId")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding voyage: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Voyage> findAll() {
        List<Voyage> voyages = new ArrayList<>();
        String sql = "SELECT * FROM voyages";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                voyages.add(new Voyage(
                        rs.getInt("id"),
                        rs.getDate("travelDate"),
                        rs.getString("destination"),
                        rs.getDouble("ticketPrice"),
                        rs.getString("conventionNumber"),
                        rs.getString("stayType"),
                        rs.getInt("stayDuration"),
                        rs.getInt("airlineId")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding voyages: " + e.getMessage());
        }
        return voyages;
    }
}
