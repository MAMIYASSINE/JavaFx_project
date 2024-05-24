package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMainFormController {

	@FXML
    private Button Airline_btn;

    @FXML
    private Button Client_btn;

    @FXML
    private Button Dashboard_btn;

    @FXML
    private Button Hotel_btn;

    @FXML
    private Button Logout_btn;

    @FXML
    private Button Voyage_btn
;

    @FXML
    private Button Reservation_btn;

    @FXML
    void directToAdminMainForm(ActionEvent event) {
        navigateTo("AdminMainForm.fxml", Dashboard_btn);
    }

    @FXML
    void directToAirlineForm(ActionEvent event) {
        navigateTo("AirlineForm.fxml", Airline_btn);
    }

    @FXML
    void directToClientForm(ActionEvent event) {
        navigateTo("ClientForm.fxml", Client_btn);
    }

    @FXML
    void directToHotelForm(ActionEvent event) {
        navigateTo("HotelForm.fxml", Hotel_btn);
    }

    @FXML
    void directToLogin(ActionEvent event) {
        navigateTo("AdminForm.fxml", Logout_btn);
    }

    @FXML
    void directToVoyageForm(ActionEvent event) {
        navigateTo("VoyageForm.fxml", Voyage_btn);
    }

    @FXML
    void directToReservationForm(ActionEvent event) {
        navigateTo("ReservationForm.fxml", Reservation_btn);
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
}
