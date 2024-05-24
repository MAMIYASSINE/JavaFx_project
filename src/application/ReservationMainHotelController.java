package application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import controller.HotelReservationService;
import controller.hotelservice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Client;
import model.Hotel;
import model.ReservationHotel;

public class ReservationMainHotelController {

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
    private Button Logout_btn;

    @FXML
    private Label clientEmailLabel;

    @FXML
    private Label clientIdLabel;

    @FXML
    private TextField clientId;

    @FXML
    private TableColumn<ReservationHotel, Integer> clientIdCol;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private TableColumn<ReservationHotel, Date> dateDebutCol;

    @FXML
    private TextField duree;

    @FXML
    private TableColumn<ReservationHotel, Integer> dureeCol;

    @FXML
    private TextField hotelId;

    @FXML
    private TableColumn<ReservationHotel, Integer> hotelIdCol;

    @FXML
    private TableView<ReservationHotel> hotelreservationTable;

    @FXML
    private TableColumn<ReservationHotel, Integer> idReservationHotelCol;

    @FXML
    private TextField nbChambre;

    @FXML
    private TableColumn<ReservationHotel, Integer> nbChambreCol;

    @FXML
    private TextField reservationId;

    @FXML
    private TableColumn<ReservationHotel, Double> totalCol;

    @FXML
    private TextField total;

    private HotelReservationService reservationHotelService = new HotelReservationService();
    private hotelservice hotelController = new hotelservice();

    @FXML
    public void initialize() {
        clientEmailLabel.setText(String.valueOf(Client.getEmailClientLogin()));
        clientIdLabel.setText(String.valueOf(Client.getIcClientLogin()));

        idReservationHotelCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        hotelIdCol.setCellValueFactory(new PropertyValueFactory<>("idHotel"));
        nbChambreCol.setCellValueFactory(new PropertyValueFactory<>("nbChambre"));
        dateDebutCol.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dureeCol.setCellValueFactory(new PropertyValueFactory<>("duree"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        findAllReservationsByClient();

        hotelreservationTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !hotelreservationTable.getSelectionModel().isEmpty()) {
                ReservationHotel selectedReservation = hotelreservationTable.getSelectionModel().getSelectedItem();
                populateFields(selectedReservation);
            }
        });
    }

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

    @FXML
    void directToLogin(ActionEvent event) {
        navigateTo("AdminForm.fxml", Logout_btn);
        Client.setIcClientLogin(0);
        Client.setEmailClientLogin(null);
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
    public void deleteReservationHotel(ActionEvent event) {
        try {
            int reservationIdValue = Integer.parseInt(reservationId.getText());
            ReservationHotel reservation = new ReservationHotel();
            reservation.setId(reservationIdValue);
            reservationHotelService.delete(reservation);
            findAllReservationsByClient();
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    @FXML
    public void updateReservationHotel(ActionEvent event) {
        try {
            int reservationIdValue = Integer.parseInt(reservationId.getText());
            int clientIdValue = Integer.parseInt(clientId.getText());
            int hotelIdValue = Integer.parseInt(hotelId.getText());
            int nbChambreValue = Integer.parseInt(nbChambre.getText());
            Date dateDebutValue = Date.valueOf(dateDebut.getValue());
            int dureeValue = Integer.parseInt(duree.getText());
            double totalValue = calculateTotal(hotelIdValue, nbChambreValue, dureeValue);

            ReservationHotel reservation = new ReservationHotel(reservationIdValue, clientIdValue, hotelIdValue, nbChambreValue, dateDebutValue, dureeValue, totalValue);
            reservationHotelService.update(reservation);
            findAllReservationsByClient();
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    @FXML
    public void EffectuerReserver(ActionEvent event) {
        try {
            int clientIdValue = Integer.parseInt(clientId.getText());
            int hotelIdValue = Integer.parseInt(hotelId.getText());
            int nbChambreValue = Integer.parseInt(nbChambre.getText());
            Date dateDebutValue = Date.valueOf(dateDebut.getValue());
            int dureeValue = Integer.parseInt(duree.getText());
            double totalValue = calculateTotal(hotelIdValue, nbChambreValue, dureeValue);

            ReservationHotel reservation = new ReservationHotel(0, clientIdValue, hotelIdValue, nbChambreValue, dateDebutValue, dureeValue, totalValue);
            reservationHotelService.create(reservation);
            findAllReservationsByClient();
            clearFields();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    private double calculateTotal(int hotelId, int nbChambre, int duree) {
        // Placeholder method to calculate the total cost. You might need to implement it.
        // For now, returning a dummy value.
    	Hotel hotel=hotelController.findById(hotelId);
        return nbChambre * duree * hotel.getPricePerRoom(); // Replace with actual calculation
    }

    public void findAllReservationsByClient() {
        int clientIdValue = Client.getIcClientLogin();
        List<ReservationHotel> reservations = reservationHotelService.findAllParClient(clientIdValue);
        hotelreservationTable.getItems().setAll(reservations);
    }

    private void populateFields(ReservationHotel reservation) {
        reservationId.setText(String.valueOf(reservation.getId()));
        clientId.setText(String.valueOf(reservation.getIdClient()));
        hotelId.setText(String.valueOf(reservation.getIdHotel()));
        nbChambre.setText(String.valueOf(reservation.getNbChambre()));
        dateDebut.setValue(reservation.getDateDebut().toLocalDate());
        duree.setText(String.valueOf(reservation.getDuree()));
        total.setText(String.valueOf(reservation.getTotal()));
    }

    private void clearFields() {
        reservationId.clear();
        clientId.clear();
        hotelId.clear();
        nbChambre.clear();
        dateDebut.setValue(null);
        duree.clear();
        total.clear();
    }
}
