package application;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
import model.Review;

public class OpinionMainController {
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
        initializeTableColumns();
        findAllReviewsByClientId();

        // Ajouter un gestionnaire d'événements pour le double-clic
        reviewTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !reviewTable.getSelectionModel().isEmpty()) {
                Review selectedReview = reviewTable.getSelectionModel().getSelectedItem();
                populateFields(selectedReview);
            }
        });
    }

    @FXML
    private TextArea reviewText;

    @FXML
    private TextField clientIdField;

    @FXML
    private TableColumn<Review, Integer> clientIdCol;

    @FXML
    private TableColumn<Review, Date> dateCol;

    @FXML
    private TextField hotelIdField;

    @FXML
    private TableColumn<Review, Integer> hotelIdCol;

    @FXML
    private DatePicker reviewDate;

    @FXML
    private TextField reviewIdField;

    @FXML
    private TableColumn<Review, Integer> reviewIdCol;

    @FXML
    private TableView<Review> reviewTable;

    @FXML
    private TableColumn<Review, String> textCol;

    private ReviewService reviewService = new ReviewService();

    private void initializeTableColumns() {
        reviewIdCol.setCellValueFactory(new PropertyValueFactory<>("reviewId"));
        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        hotelIdCol.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reviewDate"));
        textCol.setCellValueFactory(new PropertyValueFactory<>("reviewText"));
    }

    @FXML
    void createOpinion(ActionEvent event) {
        int clientId = Integer.parseInt(clientIdField.getText());
        int hotelId = Integer.parseInt(hotelIdField.getText());
        Date date = Date.valueOf(reviewDate.getValue());
        String text = reviewText.getText();

        Review newReview = new Review(clientId, hotelId, date, text);
        reviewService.create(newReview);
        findAllReviewsByClientId();
        clearFields();
    }

    @FXML
    void updateOpinion(ActionEvent event) {
        int reviewId = Integer.parseInt(reviewIdField.getText());
        int clientId = Integer.parseInt(clientIdField.getText());
        int hotelId = Integer.parseInt(hotelIdField.getText());
        Date date = Date.valueOf(reviewDate.getValue());
        String text = reviewText.getText();

        Review updatedReview = new Review(reviewId, clientId, hotelId, date, text);
        reviewService.update(updatedReview);
        findAllReviewsByClientId();
        clearFields();
    }

    @FXML
    void deleteOpinion(ActionEvent event) {
        int reviewId = Integer.parseInt(reviewIdField.getText());
        reviewService.delete(reviewId);
        findAllReviewsByClientId();
        clearFields();
    }

    public void findAllReviewsByClientId() {
        int clientId = Client.getIcClientLogin();
        List<Review> reviews = reviewService.findByClientId(clientId);
        reviewTable.getItems().setAll(reviews);
    }

    private void populateFields(Review review) {
        reviewIdField.setText(String.valueOf(review.getId()));
        clientIdField.setText(String.valueOf(review.getClientId()));
        hotelIdField.setText(String.valueOf(review.getHotelId()));
        reviewDate.setValue(review.getReviewDate().toLocalDate());
        reviewText.setText(review.getReviewText());
    }

    private void clearFields() {
        reviewIdField.clear();
        clientIdField.clear();
        hotelIdField.clear();
        reviewDate.setValue(null);
        reviewText.clear();
    }
}
