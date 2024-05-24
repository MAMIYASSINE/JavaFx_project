package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.idao;
import connexion.Connexion;
import model.Client;
import model.ReservationHotel;

public class HotelReservationService implements idao<ReservationHotel> {
    private Connection conn;

    public HotelReservationService() {
        this.conn = Connexion.getConnection();
    }

    @Override
    public boolean create(ReservationHotel reservation) {
        String sql = "INSERT INTO reservation_hotel (id_client, id_hotel, nbChambre, date_Debut, duree, total) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getIdClient());
            pstmt.setInt(2, reservation.getIdHotel());
            pstmt.setInt(3, reservation.getNbChambre());
            pstmt.setDate(4, reservation.getDateDebut());
            pstmt.setInt(5, reservation.getDuree());
            pstmt.setDouble(6, reservation.getTotal());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(ReservationHotel reservation) {
        String sql = "DELETE FROM reservation_hotel WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(ReservationHotel reservation) {
        String sql = "UPDATE reservation_hotel SET id_client = ?, id_hotel = ?, nbChambre = ?, date_Debut = ?, duree = ?, total = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservation.getIdClient());
            pstmt.setInt(2, reservation.getIdHotel());
            pstmt.setInt(3, reservation.getNbChambre());
            pstmt.setDate(4, reservation.getDateDebut());
            pstmt.setInt(5, reservation.getDuree());
            pstmt.setDouble(6, reservation.getTotal());
            pstmt.setInt(7, reservation.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ReservationHotel findById(int id) {
        String sql = "SELECT * FROM reservation_hotel WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new ReservationHotel(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("id_hotel"),
                        rs.getInt("nbChambre"),
                        rs.getDate("date_Debut"),
                        rs.getInt("duree"),
                        rs.getDouble("total")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding reservation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ReservationHotel> findAll() {
        List<ReservationHotel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation_hotel";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Client.getIcClientLogin());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reservations.add(new ReservationHotel(
                		rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("id_hotel"),
                        rs.getInt("nbChambre"),
                        rs.getDate("date_Debut"),
                        rs.getInt("duree"),
                        rs.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding reservations: " + e.getMessage());
        }
        return reservations;
    }
    public List<ReservationHotel> findAllParClient(int clientId) {
        List<ReservationHotel> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation_hotel WHERE id_client = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reservations.add(new ReservationHotel(
                    rs.getInt("id"),
                    rs.getInt("id_client"),
                    rs.getInt("id_hotel"),
                    rs.getInt("nbChambre"),
                    rs.getDate("date_Debut"),
                    rs.getInt("duree"),
                    rs.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding reservations: " + e.getMessage());
        }
        return reservations;
    }



}