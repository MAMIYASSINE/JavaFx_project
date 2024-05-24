package application;

import java.io.IOException;
import java.util.List;

import controller.hotelservice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Hotel;

public class HotelFormController {

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
    private TextField hotelIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField pricePerRoomField;

    @FXML
    private TableView<Hotel> hotelTable;
    @FXML
    private TableColumn<Hotel, Integer> hotelIdColumn;
    @FXML
    private TableColumn<Hotel, String> nameColumn;
    @FXML
    private TableColumn<Hotel, String> addressColumn;
    @FXML
    private TableColumn<Hotel, Double> pricePerRoomColumn;

    private hotelservice hotelService = new hotelservice();

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

    @FXML
    public void initialize() {
        hotelIdColumn.setCellValueFactory(new PropertyValueFactory<>("hotelId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        pricePerRoomColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerRoom"));
        findAllHotels();

        hotelTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !hotelTable.getSelectionModel().isEmpty()) {
                Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                populateFields(selectedHotel);
            }
        });
    }

    @FXML
    public void createHotel() {
        String name = nameField.getText();
        String address = addressField.getText();
        double pricePerRoom = Double.parseDouble(pricePerRoomField.getText());

        Hotel newHotel = new Hotel(name, address, pricePerRoom);
        hotelService.create(newHotel);
        findAllHotels();
        clearFields();
    }

    @FXML
    public void updateHotel() {
        int hotelId = Integer.parseInt(hotelIdField.getText());
        String name = nameField.getText();
        String address = addressField.getText();
        double pricePerRoom = Double.parseDouble(pricePerRoomField.getText());

        Hotel updatedHotel = new Hotel(hotelId, name, address, pricePerRoom);
        hotelService.update(updatedHotel);
        findAllHotels();
        clearFields();
    }

    @FXML
    public void deleteHotel() {
        int hotelId = Integer.parseInt(hotelIdField.getText());

        Hotel hotelToDelete = new Hotel(hotelId, null, null, 0);
        hotelService.delete(hotelToDelete);
        findAllHotels();
        clearFields();
    }

    @FXML
    public void findHotelById() {
        int hotelId = Integer.parseInt(hotelIdField.getText());

        Hotel foundHotel = hotelService.findById(hotelId);
        if (foundHotel != null) {
            populateFields(foundHotel);
        }
    }

    public void findAllHotels() {
        List<Hotel> hotels = hotelService.findAll();
        hotelTable.getItems().setAll(hotels);
    }

    private void populateFields(Hotel hotel) {
        hotelIdField.setText(String.valueOf(hotel.getHotelId()));
        nameField.setText(hotel.getName());
        addressField.setText(hotel.getAddress());
        pricePerRoomField.setText(String.valueOf(hotel.getPricePerRoom()));
    }

    private void clearFields() {
        hotelIdField.clear();
        nameField.clear();
        addressField.clear();
        pricePerRoomField.clear();
    }
}
