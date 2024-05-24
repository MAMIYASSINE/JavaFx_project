package model;

import java.sql.Date;

public class Voyage {
    private int id;
    private Date travelDate;
    private String destination;
    private double ticketPrice;
    private String conventionNumber;
    private String stayType;
    private int stayDuration;
    private int airlineId;

    // Constructor, getters and setters

    public Voyage(int id, Date travelDate, String destination, double ticketPrice, String conventionNumber, String stayType, int stayDuration, int airlineId) {
        this.id = id;
        this.travelDate = travelDate;
        this.destination = destination;
        this.ticketPrice = ticketPrice;
        this.conventionNumber = conventionNumber;
        this.stayType = stayType;
        this.stayDuration = stayDuration;
        this.airlineId = airlineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getConventionNumber() {
        return conventionNumber;
    }

    public void setConventionNumber(String conventionNumber) {
        this.conventionNumber = conventionNumber;
    }

    public String getStayType() {
        return stayType;
    }

    public void setStayType(String stayType) {
        this.stayType = stayType;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(int stayDuration) {
        this.stayDuration = stayDuration;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }
}
