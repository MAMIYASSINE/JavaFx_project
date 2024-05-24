package model;

public class Client {

	private int idC;
	private String nomC;
	private String prenomC;
	private String emailC;
	private String passwordC;
	private String numTelC;
	private String adresseC;
	public static int icClientLogin =0;
	public static String emailClientLogin ="";
	public Client(int idC, String nomC, String prenomC, String emailC, String passwordC, String numTelC,
			String adresseC) {
		super();
		this.idC = idC;
		this.nomC = nomC;
		this.prenomC = prenomC;
		this.emailC = emailC;
		this.passwordC = passwordC;
		this.numTelC = numTelC;
		this.adresseC = adresseC;
	}
	public Client(String nomC, String prenomC, String emailC, String passwordC, String numTelC, String adresseC) {
		super();
		this.nomC = nomC;
		this.prenomC = prenomC;
		this.emailC = emailC;
		this.passwordC = passwordC;
		this.numTelC = numTelC;
		this.adresseC = adresseC;
	}
	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	public String getNomC() {
		return nomC;
	}
	public void setNomC(String nomC) {
		this.nomC = nomC;
	}
	public String getPrenomC() {
		return prenomC;
	}
	public void setPrenomC(String prenomC) {
		this.prenomC = prenomC;
	}
	public String getEmailC() {
		return emailC;
	}
	public void setEmailC(String emailC) {
		this.emailC = emailC;
	}
	public String getPasswordC() {
		return passwordC;
	}
	public void setPasswordC(String passwordC) {
		this.passwordC = passwordC;
	}
	public String getNumTelC() {
		return numTelC;
	}
	public void setNumTelC(String numTelC) {
		this.numTelC = numTelC;
	}
	public String getAdresseC() {
		return adresseC;
	}
	public void setAdresseC(String adresseC) {
		this.adresseC = adresseC;
	}
	public static int getIcClientLogin() {
		return icClientLogin;
	}
	public static void setIcClientLogin(int icClientLogin) {
		Client.icClientLogin = icClientLogin;
	}
	public static String getEmailClientLogin() {
		return emailClientLogin;
	}
	public static void setEmailClientLogin(String i) {
		Client.emailClientLogin = i;
	}
	
	

}
