package application;

import java.io.IOException;
import java.util.List;

import controller.AirlineService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Airline;
import model.Voyage;

public class AirlineFormController {

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
    private TextField airlineIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField conventionNumberField;

    @FXML
    private TableView<Airline> airlineTable;
    @FXML
    private TableColumn<Airline, Integer> airlineIdColumn;
    @FXML
    private TableColumn<Airline, String> nameColumn;
    @FXML
    private TableColumn<Airline, String> conventionNumberColumn;

    @FXML
    private TableView<Voyage> voyageTable; // New TableView for Voyages
    @FXML
    private TableColumn<Voyage, Integer> voyageIdColumn;
    @FXML
    private TableColumn<Voyage, String> destinationColumn;
    @FXML
    private TableColumn<Voyage, String> dateColumn;

    private AirlineService airlineService = new AirlineService();

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

    @FXML
    public void initialize() {
        airlineIdColumn.setCellValueFactory(new PropertyValueFactory<>("airlineId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        conventionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("conventionNumber"));

        voyageIdColumn.setCellValueFactory(new PropertyValueFactory<>("voyageId"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        findAllAirlines();
        setupDoubleClickListener();
    }

    private void setupDoubleClickListener() {
        airlineTable.setRowFactory(tv -> {
            TableRow<Airline> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Airline rowData = row.getItem();
                    populateFields(rowData);
                    populateVoyages(rowData);
                }
            });
            return row;
        });
    }

    private void populateFields(Airline airline) {
        airlineIdField.setText(String.valueOf(airline.getAirlineId()));
        nameField.setText(airline.getName());
        conventionNumberField.setText(airline.getConventionNumber());
    }

    private void populateVoyages(Airline airline) {
        List<Voyage> voyages = airline.getVoyages();
        voyageTable.getItems().setAll(voyages);
    }

    @FXML
    public void createAirline() {
        String name = nameField.getText();
        String conventionNumber = conventionNumberField.getText();

        if (name == null || name.isEmpty() || conventionNumber == null || conventionNumber.isEmpty()) {
            System.err.println("Name or convention number is empty");
            return;
        }

        Airline newAirline = new Airline(0, name, conventionNumber); // Airline ID is auto-generated
        airlineService.create(newAirline);
        clearFields();
        findAllAirlines();
    }

    @FXML
    public void updateAirline() {
        int airlineId = Integer.parseInt(airlineIdField.getText());
        String name = nameField.getText();
        String conventionNumber = conventionNumberField.getText();

        if (name == null || name.isEmpty() || conventionNumber == null || conventionNumber.isEmpty()) {
            System.err.println("Name or convention number is empty");
            return;
        }

        Airline updatedAirline = new Airline(airlineId, name, conventionNumber);
        airlineService.update(updatedAirline);
        clearFields();
        findAllAirlines();
    }

    @FXML
    public void deleteAirline() {
        int airlineId = Integer.parseInt(airlineIdField.getText());

        Airline airlineToDelete = new Airline(airlineId, null, null);
        airlineService.delete(airlineToDelete);
        clearFields();
        findAllAirlines();
    }

    @FXML
    public void findAirlineById() {
        int airlineId = Integer.parseInt(airlineIdField.getText());

        Airline foundAirline = airlineService.findById(airlineId);
        if (foundAirline != null) {
            populateFields(foundAirline);
            populateVoyages(foundAirline);
        }
    }

    @FXML
    public void findAllAirlines() {
        List<Airline> airlines = airlineService.findAll();
        airlineTable.getItems().setAll(airlines);
    }

    private void clearFields() {
        airlineIdField.clear();
        nameField.clear();
        conventionNumberField.clear();
        voyageTable.getItems().clear();
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
