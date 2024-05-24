package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Review;
import connexion.Connexion;

public class ReviewService {
    private Connection conn;

    public ReviewService() {
        this.conn = Connexion.getConnection();
    }

    public boolean create(Review review) {
        String sql = "INSERT INTO reviews (clientId, hotelId, reviewDate, reviewText) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getClientId());
            pstmt.setInt(2, review.getHotelId());
            pstmt.setDate(3, review.getReviewDate());
            pstmt.setString(4, review.getReviewText());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error inserting review: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Review review) {
        String sql = "UPDATE reviews SET clientId = ?, hotelId = ?, reviewDate = ?, reviewText = ? WHERE reviewId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getClientId());
            pstmt.setInt(2, review.getHotelId());
            pstmt.setDate(3, review.getReviewDate());
            pstmt.setString(4, review.getReviewText());
            pstmt.setInt(5, review.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating review: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int reviewId) {
        String sql = "DELETE FROM reviews WHERE reviewId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting review: " + e.getMessage());
            return false;
        }
    }

    public Review findById(int reviewId) {
        String sql = "SELECT * FROM reviews WHERE reviewId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reviewId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Review(
                    rs.getInt("reviewId"),
                    rs.getInt("clientId"),
                    rs.getInt("hotelId"),
                    rs.getDate("reviewDate"),
                    rs.getString("reviewText")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding review: " + e.getMessage());
        }
        return null;
    }

    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                    rs.getInt("reviewId"),
                    rs.getInt("clientId"),
                    rs.getInt("hotelId"),
                    rs.getDate("reviewDate"),
                    rs.getString("reviewText")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding reviews: " + e.getMessage());
        }
        return reviews;
    }

    public List<Review> findByClientId(int clientId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE clientId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                    rs.getInt("id"),
                    rs.getInt("clientId"),
                    rs.getInt("hotelId"),
                    rs.getDate("reviewDate"),
                    rs.getString("reviewText")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error finding reviews by clientId: " + e.getMessage());
        }
        return reviews;
    }
}
