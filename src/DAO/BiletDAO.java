package DAO;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class BiletDAO extends GenericDAO<Bilet> {
    private static BiletDAO instance;

    private BiletDAO() {
        super();
    }

    public static BiletDAO getInstance() {
        if (instance == null) {
            instance = new BiletDAO();
        }
        return instance;
    }

    @Override
    public void create(Bilet bilet) {
        String sql = "INSERT INTO Bilet (id_eveniment, id_client, locuri, pret, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bilet.getEveniment().getId());
            stmt.setInt(2, bilet.getClient().getId());

            String locuriText = String.join(",", bilet.getLocuri().stream().map(String::valueOf).toList());
            stmt.setString(3, locuriText);

            stmt.setDouble(4, bilet.getPret());
            stmt.setString(5, bilet.getValidat().name());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bilet.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bilet read(int id) {
        String sql = "SELECT * FROM Bilet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Eveniment eveniment = EvenimentDAO.getInstance().read(rs.getInt("id_eveniment"));
                    Client client = ClientDAO.getInstance().read(rs.getInt("id_client"));

                    String locuriText = rs.getString("locuri");
                    Set<Integer> locuri = new HashSet<>();
                    if (locuriText != null && !locuriText.isEmpty()) {
                        locuri = new HashSet<>(Arrays.stream(locuriText.split(","))
                                .map(Integer::parseInt)
                                .toList());
                    }

                    return new Bilet(
                            rs.getInt("id"),
                            eveniment,
                            client,
                            locuri,
                            rs.getDouble("pret"),
                            Status.valueOf(rs.getString("status"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Bilet bilet) {
        String sql = "UPDATE Bilet SET id_eveniment = ?, id_client = ?, locuri = ?, pret = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bilet.getEveniment().getId());
            stmt.setInt(2, bilet.getClient().getId());

            String locuriText = String.join(",", bilet.getLocuri().stream().map(String::valueOf).toList());
            stmt.setString(3, locuriText);

            stmt.setDouble(4, bilet.getPret());
            stmt.setString(5, bilet.getValidat().name());
            stmt.setInt(6, bilet.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Bilet WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bilet> readAll() {
        List<Bilet> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bilet";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Eveniment eveniment = EvenimentDAO.getInstance().read(rs.getInt("id_eveniment"));
                Client client = ClientDAO.getInstance().read(rs.getInt("id_client"));
                double pret = rs.getDouble("pret");
                Status status = Status.valueOf(rs.getString("status"));
                int id = rs.getInt("id");

                // Pentru simplificare, se presupune că locurile nu sunt stocate direct în DB
                Bilet bilet = new Bilet(id, eveniment, client, new HashSet<>(), pret, status);
                lista.add(bilet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Bilet> readByClientId(int idClient) {
        List<Bilet> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bilet WHERE id_client = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Eveniment eveniment = EvenimentDAO.getInstance().read(rs.getInt("id_eveniment"));
                    Client client = ClientDAO.getInstance().readWithoutBilete(rs.getInt("id_client"));
                    double pret = rs.getDouble("pret");
                    Status status = Status.valueOf(rs.getString("status"));
                    int id = rs.getInt("id");

                    String locuriText = rs.getString("locuri");
                    Set<Integer> locuri = new HashSet<>();
                    if (locuriText != null && !locuriText.isEmpty()) {
                        locuri = Arrays.stream(locuriText.split(","))
                                .map(String::trim)
                                .map(Integer::parseInt)
                                .collect(Collectors.toSet());
                    }

                    Bilet bilet = new Bilet(id, eveniment, client, locuri, pret, status);
                    lista.add(bilet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}
