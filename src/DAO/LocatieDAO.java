package DAO;

import Entitati.Locatie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocatieDAO extends GenericDAO<Locatie> {
    private static LocatieDAO instance;

    private LocatieDAO() {
        super();
    }

    public static LocatieDAO getInstance() {
        if (instance == null) {
            instance = new LocatieDAO();
        }
        return instance;
    }

    @Override
    public void create(Locatie locatie) {
        String sql = "INSERT INTO Locatie (nume, adresa, capacitate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, locatie.getNume());
            stmt.setString(2, locatie.getAdresa());
            stmt.setInt(3, locatie.getCapacitate());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    locatie.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Locatie read(int id) {
        String sql = "SELECT * FROM Locatie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Locatie(
                            rs.getInt("id"),
                            rs.getString("nume"),
                            rs.getString("adresa"),
                            rs.getInt("capacitate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Locatie locatie) {
        String sql = "UPDATE Locatie SET nume = ?, adresa = ?, capacitate = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, locatie.getNume());
            stmt.setString(2, locatie.getAdresa());
            stmt.setInt(3, locatie.getCapacitate());
            stmt.setInt(4, locatie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Locatie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă opțională: citește toate locațiile
    public List<Locatie> readAll() {
        List<Locatie> locatii = new ArrayList<>();
        String sql = "SELECT * FROM Locatie";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                locatii.add(new Locatie(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("adresa"),
                        rs.getInt("capacitate")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locatii;
    }
}
