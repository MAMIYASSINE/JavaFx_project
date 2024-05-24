package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;
import connexion.Connexion;

public class ClientDatabase {

	private Connection connection;

	public ClientDatabase() {
		this.connection = Connexion.getConnection();
	}

	// Create a new client in the database
	public boolean create(Client client) {
	    String query = "INSERT INTO client(nomC, prenomC, emailC, passwordC, numTelC, adresseC) VALUES(?, ?, ?, ?, ?, ?)";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, client.getNomC());
	        stmt.setString(2, client.getPrenomC());
	        stmt.setString(3, client.getEmailC());
	        stmt.setString(4, client.getPasswordC());
	        stmt.setString(5, client.getNumTelC());
	        stmt.setString(6, client.getAdresseC());

	        int results = stmt.executeUpdate();
	        System.out.println("Insertion result: " + results);
	        return results > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	// Delete a client from the database
	public boolean delete(Client client) {
		String query = "DELETE FROM client WHERE idC = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, client.getIdC());

			int results = stmt.executeUpdate();
			return results > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Update a client in the database
	public boolean update(Client client) {
		String query = "UPDATE client SET nomC = ?, prenomC = ?, emailC = ?, passwordC = ?, numTelC = ?, adresseC = ? WHERE idC = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, client.getNomC());
			stmt.setString(2, client.getPrenomC());
			stmt.setString(3, client.getEmailC());
			stmt.setString(4, client.getPasswordC());
			stmt.setString(5, client.getNumTelC());
			stmt.setString(6, client.getAdresseC());
			stmt.setInt(7, client.getIdC());

			int results = stmt.executeUpdate();
			return results > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Find a client by ID
	public Client findById(int id) {
		String query = "SELECT * FROM client WHERE idC = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Client(rs.getInt("idC"), rs.getString("nomC"), rs.getString("prenomC"),
							rs.getString("emailC"), rs.getString("passwordC"), rs.getString("numTelC"),
							rs.getString("adresseC"));
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Find all clients
	public List<Client> findAll() {
		String query = "SELECT * FROM client";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();

			List<Client> clients = new ArrayList<>();
			while (rs.next()) {
				Client client = new Client(rs.getInt("idC"), rs.getString("nomC"), rs.getString("prenomC"),
						rs.getString("emailC"), rs.getString("passwordC"), rs.getString("numTelC"),
						rs.getString("adresseC"));
				clients.add(client);
			}
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	public boolean clientExistsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM client WHERE emailC = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	// Find a client by email and password
	public Client findByEmailAndPassword(String email, String password) {
	    String query = "SELECT * FROM client WHERE emailC = ? AND passwordC = ?";
	    try {
	        PreparedStatement stmt = connection.prepareStatement(query);
	        stmt.setString(1, email);
	        stmt.setString(2, password);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new Client(
	                    rs.getInt("idC"), 
	                    rs.getString("nomC"), 
	                    rs.getString("prenomC"),
	                    rs.getString("emailC"), 
	                    rs.getString("passwordC"), 
	                    rs.getString("numTelC"),
	                    rs.getString("adresseC")
	                );
	            } else {
	                return null;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
}
