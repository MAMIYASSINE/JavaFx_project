package model;

import java.util.List;

public class Airline {

    private int airlineId;
    private String name;
    private String conventionNumber;
    private List<Voyage> voyages;

    public Airline(int airlineId, String name, String conventionNumber) {
        this.airlineId = airlineId;
        this.name = name;
        this.conventionNumber = conventionNumber;
    }

    public Airline(String name, String conventionNumber) {
        this.name = name;
        this.conventionNumber = conventionNumber;
    }

    // Getters et Setters
    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConventionNumber() {
        return conventionNumber;
    }

    public void setConventionNumber(String conventionNumber) {
        this.conventionNumber = conventionNumber;
    }

	public List<Voyage> getVoyages() {
		return voyages;
	}

	public void setVoyages(List<Voyage> voyages) {
		this.voyages = voyages;
	}
}
