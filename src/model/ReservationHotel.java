package model;

import java.sql.Date;

public class ReservationHotel {
    private int id;
    private int idClient;
    private int idHotel;
    private int nbChambre;
    private java.sql.Date dateDebut;
    private int duree;
    private double total;

    // Constructor
    public ReservationHotel(int id, int idClient, int idHotel, int nbChambre, java.sql.Date dateDebut, int duree, double total) {
        this.id = id;
        this.idClient = idClient;
        this.idHotel = idHotel;
        this.nbChambre = nbChambre;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.total = total;
    }
    

    public ReservationHotel(int idClient, int idHotel, int nbChambre, Date dateDebut, int duree, double total) {
		super();
		this.idClient = idClient;
		this.idHotel = idHotel;
		this.nbChambre = nbChambre;
		this.dateDebut = dateDebut;
		this.duree = duree;
		this.total = total;
	}
    public ReservationHotel()
    {
    	
    }


	// Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(int nbChambre) {
        this.nbChambre = nbChambre;
    }

    public java.sql.Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(java.sql.Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

