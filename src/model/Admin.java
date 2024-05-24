package model;

public class Admin {
	private int id;
	private String nomA;
	private String prenomA;
	private String emailA;
	private String passwordA;
	private String numTelA;
	public Admin(int id, String nomA, String prenomA, String emailA, String passwordA, String numTelA) {
		super();
		this.id = id;
		this.nomA = nomA;
		this.prenomA = prenomA;
		this.emailA = emailA;
		this.passwordA = passwordA;
		this.numTelA = numTelA;
	}
	public Admin(String nomA, String prenomA, String emailA, String passwordA, String numTelA) {
		super();
		this.nomA = nomA;
		this.prenomA = prenomA;
		this.emailA = emailA;
		this.passwordA = passwordA;
		this.numTelA = numTelA;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomA() {
		return nomA;
	}
	public void setNomA(String nomA) {
		this.nomA = nomA;
	}
	public String getPrenomA() {
		return prenomA;
	}
	public void setPrenomA(String prenomA) {
		this.prenomA = prenomA;
	}
	public String getEmailA() {
		return emailA;
	}
	public void setEmailA(String emailA) {
		this.emailA = emailA;
	}
	public String getPasswordA() {
		return passwordA;
	}
	public void setPasswordA(String passwordA) {
		this.passwordA = passwordA;
	}
	public String getNumTelA() {
		return numTelA;
	}
	public void setNumTelA(String numTelA) {
		this.numTelA = numTelA;
	}
	
	

}
