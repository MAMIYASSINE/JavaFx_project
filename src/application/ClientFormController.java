package application;

import java.io.IOException;
import java.util.List;

import controller.ClientDatabase;
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
import model.Client;

public class ClientFormController {

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
    private TextField clientAdress;

    @FXML
    private TableColumn<Client, String> clientAdressCol;

    @FXML
    private TextField clientEmail;

    @FXML
    private TableColumn<Client, String> clientEmailCol;

    @FXML
    private TextField clientId;

    @FXML
    private TableColumn<Client, Integer> clientIdCol;

    @FXML
    private TextField clientNom;

    @FXML
    private TableColumn<Client, String> clientNomCol;

    @FXML
    private TextField clientPass;

    @FXML
    private TableColumn<Client, String> clientPassCol;

    @FXML
    private TextField clientPrenom;

    @FXML
    private TableColumn<Client, String> clientPrenomCol;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TextField clientTel;

    @FXML
    private TableColumn<Client, String> clientTelCol;

    private ClientDatabase clientDatabase = new ClientDatabase();

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
        clientIdCol.setCellValueFactory(new PropertyValueFactory<>("idC"));
        clientNomCol.setCellValueFactory(new PropertyValueFactory<>("nomC"));
        clientPrenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomC"));
        clientEmailCol.setCellValueFactory(new PropertyValueFactory<>("emailC"));
        clientPassCol.setCellValueFactory(new PropertyValueFactory<>("passwordC"));
        clientTelCol.setCellValueFactory(new PropertyValueFactory<>("numTelC"));
        clientAdressCol.setCellValueFactory(new PropertyValueFactory<>("adresseC"));
        findAllClients();

        clientTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !clientTable.getSelectionModel().isEmpty()) {
                Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
                populateFields(selectedClient);
            }
        });
    }

    @FXML
    public void createClient(ActionEvent event) {
        String nom = clientNom.getText();
        String prenom = clientPrenom.getText();
        String email = clientEmail.getText();
        String password = clientPass.getText();
        String tel = clientTel.getText();
        String adresse = clientAdress.getText();

        Client newClient = new Client(nom, prenom, email, password, tel, adresse);
        clientDatabase.create(newClient);
        findAllClients();
        clearFields();
    }

    @FXML
    public void updateClient(ActionEvent event) {
        int id = Integer.parseInt(clientId.getText());
        String nom = clientNom.getText();
        String prenom = clientPrenom.getText();
        String email = clientEmail.getText();
        String password = clientPass.getText();
        String tel = clientTel.getText();
        String adresse = clientAdress.getText();

        Client updatedClient = new Client(id, nom, prenom, email, password, tel, adresse);
        clientDatabase.update(updatedClient);
        findAllClients();
        clearFields();
    }

    @FXML
    public void deleteClient(ActionEvent event) {
        int id = Integer.parseInt(clientId.getText());

        Client clientToDelete = new Client(id, null, null, null, null, null, null);
        clientDatabase.delete(clientToDelete);
        findAllClients();
        clearFields();
    }

    @FXML
    public void findClientById(ActionEvent event) {
        int id = Integer.parseInt(clientId.getText());

        Client foundClient = clientDatabase.findById(id);
        if (foundClient != null) {
            populateFields(foundClient);
        }
    }

    public void findAllClients() {
        List<Client> clients = clientDatabase.findAll();
        clientTable.getItems().setAll(clients);
    }

    private void populateFields(Client client) {
        clientId.setText(String.valueOf(client.getIdC()));
        clientNom.setText(client.getNomC());
        clientPrenom.setText(client.getPrenomC());
        clientEmail.setText(client.getEmailC());
        clientPass.setText(client.getPasswordC());
        clientTel.setText(client.getNumTelC());
        clientAdress.setText(client.getAdresseC());
    }

    private void clearFields() {
        clientId.clear();
        clientNom.clear();
        clientPrenom.clear();
        clientEmail.clear();
        clientPass.clear();
        clientTel.clear();
        clientAdress.clear();
    }
}
