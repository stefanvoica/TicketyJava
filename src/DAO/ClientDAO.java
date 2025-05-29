package DAO;

import Entitati.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO extends GenericDAO<Client> {
    private static ClientDAO instance;

    private ClientDAO() {
        super();
    }

    public static ClientDAO getInstance() {
        if (instance == null) {
            instance = new ClientDAO();
        }
        return instance;
    }

    @Override
    public void create(Client client) {
        String sql = "INSERT INTO Client (nume, email, varsta, adresa, numar_bilete) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getEmail());
            stmt.setInt(3, client.getVarsta());
            stmt.setString(4, client.getAdresa());
            stmt.setInt(5, client.getNumarBilete());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client read(int id) {
        String sql = "SELECT * FROM Client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("email"),
                            rs.getInt("varsta"),
                            rs.getString("adresa"),
                            rs.getInt("numar_bilete"),
                            new ArrayList<>() // biletele pot fi adăugate separat
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Client client) {
        String sql = "UPDATE Client SET nume = ?, email = ?, varsta = ?, adresa = ?, numar_bilete = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getEmail());
            stmt.setInt(3, client.getVarsta());
            stmt.setString(4, client.getAdresa());
            stmt.setInt(5, client.getNumarBilete());
            stmt.setInt(6, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Client WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> readAll() {
        List<Client> clienti = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                clienti.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("email"),
                        rs.getInt("varsta"),
                        rs.getString("adresa"),
                        rs.getInt("numar_bilete"),
                        new ArrayList<>()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienti;
    }
}
