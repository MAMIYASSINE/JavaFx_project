package application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import controller.hotelservice;
import controller.HotelReservationService;
import controller.ReviewService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Client;
import model.Hotel;
import model.ReservationHotel;
import model.Review;

public class HotelMainController {

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
    private TableColumn<Hotel, String> addressColumn;

    @FXML
    private TextField clientId;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private TextField duree;

    @FXML
    private TextField hotelId;

    @FXML
    private TableColumn<Hotel, Integer> hotelIdColumn;

    @FXML
    private TableView<Hotel> hotelTable;

    @FXML
    private TableColumn<Hotel, String> nameColumn;

    @FXML
    private TextField nbChambre;
    @FXML
    private TextArea reviewText;
    @FXML
    private Label clientEmailLabel;

  @FXML
    private Label clientIdLabel;


    @FXML
    private TableColumn<Hotel, Double> pricePerRoomColumn;

    private hotelservice hotelService = new hotelservice();
    private HotelReservationService reservationHotelService = new HotelReservationService();

    @FXML
    public void initialize() {
        hotelIdColumn.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        pricePerRoomColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerRoom"));
        findAllHotels();
        clientEmailLabel.setText(String.valueOf(Client.getEmailClientLogin()));
        clientIdLabel.setText(String.valueOf(Client.getIcClientLogin()));

        hotelTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !hotelTable.getSelectionModel().isEmpty()) {
                Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                populateHotelIdField(selectedHotel);
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
    void EffectuerReserver(ActionEvent event) {
        try {
            int clientIdValue = Integer.parseInt(clientId.getText());
            int hotelIdValue = Integer.parseInt(hotelId.getText());
            int nbChambreValue = Integer.parseInt(nbChambre.getText());
            Date dateDebutValue = Date.valueOf(dateDebut.getValue());
            int dureeValue = Integer.parseInt(duree.getText());

            Hotel selectedHotel = hotelService.findById(hotelIdValue);
            if (selectedHotel != null) {
                double pricePerRoom = selectedHotel.getPricePerRoom();
                double total = dureeValue * pricePerRoom * nbChambreValue;

                ReservationHotel reservation = new ReservationHotel(clientIdValue, hotelIdValue, nbChambreValue, dateDebutValue, dureeValue, total);
                reservationHotelService.create(reservation);

                clearFields();
            } else {
                System.out.println("Hotel not found!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    public void findAllHotels() {
        List<Hotel> hotels = hotelService.findAll();
        hotelTable.getItems().setAll(hotels);
    }

    private void populateHotelIdField(Hotel hotel) {
        hotelId.setText(String.valueOf(hotel.getHotelId()));
        clientId.setText(String.valueOf(Client.getIcClientLogin()));         
    }

    private void clearFields() {
        clientId.clear();
        hotelId.clear();
        nbChambre.clear();
        dateDebut.setValue(null);
        duree.clear();
    }

@FXML
void EffectuerOpinion(ActionEvent event) {
    try {
        int clientIdValue = Integer.parseInt(clientId.getText());
        int hotelIdValue = Integer.parseInt(hotelId.getText());
        Date reviewDateValue = Date.valueOf(dateDebut.getValue());
        String reviewTextValue = reviewText.getText();

        Review review = new Review(0, clientIdValue, hotelIdValue, reviewDateValue, reviewTextValue);
        ReviewService reviewService = new ReviewService();
        reviewService.create(review);

        clearFields();
    } catch (NumberFormatException e ) {
        System.out.println("Invalid input or SQL error: " + e.getMessage());
    }
}

}