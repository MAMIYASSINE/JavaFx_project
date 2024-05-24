package application;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Voyage;
import java.time.LocalDate;
import controller.VoyageService;

import java.sql.Date;
import java.time.LocalDate;


public class VoyageFormController {

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
    private Button Voyage_btn;
    @FXML
    private Button Reservation_btn;
    @FXML
    private TextField voyageAirlineId;
    @FXML
    private TableColumn<Voyage, Integer> voyageAirlineIdCol;
    @FXML
    private TextField voyageConvention;
    @FXML
    private TableColumn<Voyage, String> voyageConventionCol;
    @FXML
    private TextField voyageDestination;
    @FXML
    private TableColumn<Voyage, String> voyageDestinationCol;
    @FXML
    private TextField voyageDuration;
    @FXML
    private TableColumn<Voyage, Integer> voyageDurationCol;
    @FXML
    private TextField voyageId;
    @FXML
    private TableColumn<Voyage, Integer> voyageIdCol;
    @FXML
    private TextField voyagePrice;
    @FXML
    private TableColumn<Voyage, Double> voyagePriceCol;
    @FXML
    private TableView<Voyage> voyageTable;
    @FXML
    private DatePicker voyageTravelDate;
    @FXML
    private TableColumn<Voyage, java.sql.Date> voyageTravelDateCol;
    @FXML
    private TextField voyagetype;
    @FXML
    private TableColumn<Voyage, String> voyagetypeCol;

    private VoyageService voyageService = new VoyageService();
    private java.sql.Date travelDate;


    @FXML
    public void initialize() {
        voyageIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        voyageDestinationCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        voyageTravelDateCol.setCellValueFactory(new PropertyValueFactory<>("travelDate"));
        voyagePriceCol.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        voyageConventionCol.setCellValueFactory(new PropertyValueFactory<>("conventionNumber"));
        voyagetypeCol.setCellValueFactory(new PropertyValueFactory<>("stayType"));
        voyageDurationCol.setCellValueFactory(new PropertyValueFactory<>("stayDuration"));
        voyageAirlineIdCol.setCellValueFactory(new PropertyValueFactory<>("airlineId"));

        loadVoyages();

        voyageTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !voyageTable.getSelectionModel().isEmpty()) {
                Voyage selectedVoyage = voyageTable.getSelectionModel().getSelectedItem();
                populateFields(selectedVoyage);
            }
        });
    }

    @FXML
    private void createVoyage(ActionEvent event) {
        try {
            String destination = voyageDestination.getText();
            LocalDate travelDate = voyageTravelDate.getValue();
            double ticketPrice = Double.parseDouble(voyagePrice.getText());
            String conventionNumber = voyageConvention.getText();
            String stayType = voyagetype.getText();
            int stayDuration = Integer.parseInt(voyageDuration.getText());
            int airlineId = Integer.parseInt(voyageAirlineId.getText());

            // Create a new Voyage object
            Voyage newVoyage = new Voyage(0, java.sql.Date.valueOf(travelDate), destination, ticketPrice, conventionNumber, stayType, stayDuration, airlineId);

            // Call the service to save the new voyage
            boolean success = voyageService.create(newVoyage);

            if (success) {
                // Reload the voyages and clear fields
                loadVoyages();
                clearFields();
            } else {
                System.err.println("Failed to create new voyage");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void updateVoyage(ActionEvent event) {
        try {
            int id = Integer.parseInt(voyageId.getText());
            String destination = voyageDestination.getText();
            LocalDate travelDate = voyageTravelDate.getValue();
            double ticketPrice = Double.parseDouble(voyagePrice.getText());
            String conventionNumber = voyageConvention.getText();
            String stayType = voyagetype.getText();
            int stayDuration = Integer.parseInt(voyageDuration.getText());
            int airlineId = Integer.parseInt(voyageAirlineId.getText());

            Voyage updatedVoyage = new Voyage(id, java.sql.Date.valueOf(travelDate), destination, ticketPrice, conventionNumber, stayType, stayDuration, airlineId);
            boolean success = voyageService.update(updatedVoyage);

            if (success) {
                loadVoyages();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteVoyage(ActionEvent event) {
        try {
            int id = Integer.parseInt(voyageId.getText());
            Voyage voyageToDelete = new Voyage(id, null, null, 0, null, null, 0, 0);
            boolean success = voyageService.delete(voyageToDelete);
            if (success) {
                loadVoyages();
                clearFields();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void findVoyageById(ActionEvent event) {
        try {
            int id = Integer.parseInt(voyageId.getText());
            Voyage foundVoyage = voyageService.findById(id);
            if (foundVoyage != null) {
                populateFields(foundVoyage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadVoyages() {
        List<Voyage> voyages = voyageService.findAll();
        voyageTable.getItems().setAll(voyages);
    }

    private void populateFields(Voyage voyage) {
        voyageId.setText(String.valueOf(voyage.getId()));
        voyageDestination.setText(voyage.getDestination());

        // Convertir java.sql.Date en java.time.LocalDate
        java.sql.Date sqlDate = voyage.getTravelDate();
        if (sqlDate != null) {
            LocalDate localDate = sqlDate.toLocalDate();
            voyageTravelDate.setValue(localDate);
        } else {
            voyageTravelDate.setValue(null);
        }

        voyagePrice.setText(String.valueOf(voyage.getTicketPrice()));
        voyageConvention.setText(voyage.getConventionNumber());
        voyagetype.setText(voyage.getStayType());
        voyageDuration.setText(String.valueOf(voyage.getStayDuration()));
        voyageAirlineId.setText(String.valueOf(voyage.getAirlineId()));
    }


    private void clearFields() {
        voyageId.clear();
        voyageDestination.clear();
        voyageTravelDate.setValue(null);
        voyagePrice.clear();
        voyageConvention.clear();
        voyagetype.clear();
        voyageDuration.clear();
        voyageAirlineId.clear();
    }

    @FXML
    private void directToAdminMainForm(ActionEvent event) {
        navigateTo("AdminMainForm.fxml", Dashboard_btn);
    }

    @FXML
    private void directToAirlineForm(ActionEvent event) {
        navigateTo("AirlineForm.fxml", Airline_btn);
    }

    @FXML
    private void directToClientForm(ActionEvent event) {
        navigateTo("ClientForm.fxml", Client_btn);
    }

    @FXML
    private void directToHotelForm(ActionEvent event) {
        navigateTo("HotelForm.fxml", Hotel_btn);
    }

    @FXML
    private void directToLogin(ActionEvent event) {
        navigateTo("AdminForm.fxml", Logout_btn);
    }

    @FXML
    private void directToVoyageForm(ActionEvent event) {
        navigateTo("VoyageForm.fxml", Voyage_btn);
    }

    @FXML
    private void directToReservationForm(ActionEvent event) {
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
