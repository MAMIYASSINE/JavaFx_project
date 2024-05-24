package model;

import java.sql.Date;

public class Review {
    private int id;
    private int clientId;
    private int hotelId;
    private Date reviewDate;
    private String reviewText;

    public Review(int id, int clientId, int hotelId, Date reviewDate, String reviewText) {
        this.id = id;
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.reviewDate = reviewDate;
        this.reviewText = reviewText;
    }

    public Review(int clientId, int hotelId, Date reviewDate, String reviewText) {
		super();
		this.clientId = clientId;
		this.hotelId = hotelId;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
