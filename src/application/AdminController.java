package application;

import java.net.URL;
import java.util.ResourceBundle;

import controller.AdminDatabase;
import controller.ClientDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Admin;
import model.Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class AdminController implements Initializable{

	@FXML
    private Button login_btn;

    @FXML
    private TextField login_email;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private ComboBox<String> login_user;
    
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField register_adress;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private TextField register_nom;

    @FXML
    private TextField register_numTel;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_prenom;

    @FXML
    private Button register_signUP_btn;
    
    private AlertMessage alert=new AlertMessage();
    private ClientDatabase clientDatabase=new ClientDatabase();
    private AdminDatabase adminDatabase=new AdminDatabase();
    private void showAdminMainForm() {
        try {
            // Chargez le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMainForm.fxml"));
            Parent root = loader.load();

            // Obtenez la scène actuelle
            Stage stage = (Stage) login_btn.getScene().getWindow();

            // Créez une nouvelle scène avec le contenu chargé
            Scene scene = new Scene(root);

            // Définissez la nouvelle scène sur la fenêtre (stage)
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showhotelMain() {
        try {
            // Chargez le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HotelMain.fxml"));
            Parent root = loader.load();

            // Obtenez la scène actuelle
            Stage stage = (Stage) login_btn.getScene().getWindow();

            // Créez une nouvelle scène avec le contenu chargé
            Scene scene = new Scene(root);

            // Définissez la nouvelle scène sur la fenêtre (stage)
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void login(ActionEvent event) {
        String email = login_email.getText();
        String password = login_password.getText();
        String selectedUser = login_user.getValue();
        System.out.println("Selected User: " + selectedUser); // Debugging output

        if (email.isEmpty() || password.isEmpty() || selectedUser == null) {
            alert.errorMessage("Please fill all blanks fields");
        } else {
            if (selectedUser.equals("Client")) {
                Client client = clientDatabase.findByEmailAndPassword(email, password);
                if (client != null) {
                    alert.successMessage("Login successful");
                    Client.setIcClientLogin(client.getIdC());
                    Client.setEmailClientLogin(client.getEmailC());
                    showhotelMain();

                    // Redirect to client dashboard
                } else {
                    alert.errorMessage("Invalid email or password");
                    login_email.clear();
                    login_password.clear();
                }
            } else if (selectedUser.equals("Admin")) {
                Admin admin = adminDatabase.findByEmailAndPassword(email, password);
                if (admin != null) {
                    alert.successMessage("Login successful Admin");
                    showAdminMainForm();

                    // Redirect to admin dashboard
                } else {
                    alert.errorMessage("Invalid email or password Admin");
                    login_email.clear();
                    login_password.clear();
                }
            }
        }
    }

    public void registerAccount() {
    	String nom = register_nom.getText();
        String prenom = register_prenom.getText();
        String tel = register_numTel.getText();
        String email = register_email.getText();
        String password = register_password.getText();
        String adress = register_adress.getText();
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty() || password.isEmpty() || adress.isEmpty()) {
        	alert.errorMessage("Please fill all blanks fields");
        }
        else
        {
        	if (clientDatabase.clientExistsByEmail(email)) {
        		alert.errorMessage(email+" is already exist ");
        		register_email.clear();
        		
        	}
        	else if (clientDatabase.create(new Client(nom,prenom,email,password,tel,adress)))
        	{
        		alert.successMessage("Registred successfully");
        		 clearRegisterFields();  
                 switchToLoginForm();    
        		
        	}
        	else
        	{
        		alert.errorMessage(" Registred error");

        	}
        }
    }
    private void clearRegisterFields() {
        register_nom.clear();
        register_prenom.clear();
        register_numTel.clear();
        register_email.clear();
        register_password.clear();
        register_adress.clear();
    }

    private void switchToLoginForm() {
        login_form.setVisible(true);
        register_form.setVisible(false);
    }
    public void switchForm(ActionEvent event) {
    	if (event.getSource()== login_registerHere) {
    		login_form.setVisible(false);
    		register_form.setVisible(true);
    	}
    	else if (event.getSource()==register_loginHere) {
    		login_form.setVisible(true);
    		register_form.setVisible(false);
    	}
        	
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login_user.getItems().addAll("Client", "Admin");

    }
    @FXML
    private void handleLoginUserSelection(ActionEvent event) {
        String selectedUser = login_user.getValue();
        // Faites quelque chose en fonction de l'utilisateur sélectionné
        System.out.println("Selected User: " + selectedUser);
    }

}
