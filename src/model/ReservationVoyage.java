package model;

public class ReservationVoyage {
    private int id;
    private int idClient;
    private int idVoyage;
    private int nombrePersonne;
    private double total;

    // Constructor
    public ReservationVoyage(int id, int idClient, int idVoyage, int nombrePersonne, double total) {
        this.id = id;
        this.idClient = idClient;
        this.idVoyage = idVoyage;
        this.nombrePersonne = nombrePersonne;
        this.total = total;
    }
    

    public ReservationVoyage(int idClient, int idVoyage, int nombrePersonne, double total) {
		super();
		this.idClient = idClient;
		this.idVoyage = idVoyage;
		this.nombrePersonne = nombrePersonne;
		this.total = total;
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

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(int nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

