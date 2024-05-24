package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Client;

public class ClientMainController {

	@FXML
	private Button hotel_btn;

	@FXML
	private Button opinion_btn;

	@FXML
	private Button reservationHotel_btn;

	@FXML
	private Button reservationVoyage_btn;

	@FXML
	private Button voyage_btn;

	@FXML
	void directToVoyage(ActionEvent event) {

		navigateTo("VoyageMain.fxml", voyage_btn);

	}

	@FXML
	void directToHotel(ActionEvent event) {

		navigateTo("HotelMain.fxml", hotel_btn);

	}

	@FXML
	void directToOpinion(ActionEvent event) {

		navigateTo("OpinionMain.fxml", opinion_btn);

	}

	@FXML
	void directToReservationHotel(ActionEvent event) {
		navigateTo("ReservationMainHotel.fxml", reservationHotel_btn);

	}

	@FXML
	void directToReservationVoyage(ActionEvent event) {
		navigateTo("ReservationMainVoyage.fxml", reservationVoyage_btn);

	}

	private void navigateTo(String fxml, Button button) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Parent root = loader.load();
			Stage stage = (Stage) button.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button Logout_btn;

	@FXML
	void directToLogin(ActionEvent event) {
		navigateTo("AdminForm.fxml", Logout_btn);
		Client.setIcClientLogin(0);
		Client.setEmailClientLogin(null);
	}

	@FXML
	private Label clientEmailLabel;

	@FXML
	private Label clientIdLabel;

	@FXML
	public void initialize() {
		
		clientEmailLabel.setText(String.valueOf(Client.getEmailClientLogin()));
        clientIdLabel.setText(String.valueOf(Client.getIcClientLogin()));
	}
	 

	

}
