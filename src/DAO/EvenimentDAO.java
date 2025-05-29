package DAO;

import Entitati.Eveniment;
import Entitati.EvenimentCultural;
import Entitati.EvenimentSportiv;
import Entitati.Locatie;
import Utile.TipEveniment;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EvenimentDAO extends GenericDAO<Eveniment> {
    private static EvenimentDAO instance;

    private EvenimentDAO() { super(); }

    public static EvenimentDAO getInstance() {
        if (instance == null) instance = new EvenimentDAO();
        return instance;
    }

    @Override
    public void create(Eveniment eveniment) {
        String sql = "INSERT INTO Eveniment (nume, tip, subtip, data, id_locatie, echipa1, echipa2, esteDerby, durata, limba) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, eveniment.getNume());
            stmt.setString(2, eveniment.getTip().name());

            stmt.setTimestamp(4, Timestamp.valueOf(eveniment.getData()));
            stmt.setInt(5, eveniment.getLocatie().getId());

            // Sportiv
            if (eveniment instanceof EvenimentSportiv evSport) {
                stmt.setString(3, "SPORTIV");
                stmt.setString(6, evSport.getEchipa1());
                stmt.setString(7, evSport.getEchipa2());
                stmt.setBoolean(8, evSport.isEsteDerby());
                stmt.setNull(9, Types.INTEGER);
                stmt.setNull(10, Types.VARCHAR);
            }
            // Cultural
            else if (eveniment instanceof EvenimentCultural evCult) {
                stmt.setString(3, "CULTURAL");
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.BOOLEAN);
                stmt.setInt(9, evCult.getDurata());
                stmt.setString(10, evCult.getLimba());
            }
            // Fallback
            else {
                stmt.setString(3, null);
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.BOOLEAN);
                stmt.setNull(9, Types.INTEGER);
                stmt.setNull(10, Types.VARCHAR);
            }

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) eveniment.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Eveniment read(int id) {
        String sql = "SELECT * FROM Eveniment e JOIN Locatie l ON e.id_locatie = l.id WHERE e.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Locatie locatie = new Locatie(
                            rs.getInt("l.id"),
                            rs.getString("l.nume"),
                            rs.getString("l.adresa"),
                            rs.getInt("l.capacitate")
                    );

                    TipEveniment tip = TipEveniment.valueOf(rs.getString("e.tip"));
                    String subtip = rs.getString("e.subtip");
                    String nume = rs.getString("e.nume");
                    LocalDateTime data = rs.getTimestamp("e.data").toLocalDateTime();

                    if ("SPORTIV".equalsIgnoreCase(subtip)) {
                        return new EvenimentSportiv(
                                rs.getInt("e.id"),
                                nume,
                                locatie,
                                data,
                                rs.getString("e.echipa1"),
                                rs.getString("e.echipa2"),
                                rs.getBoolean("e.esteDerby")
                        );
                    } else if ("CULTURAL".equalsIgnoreCase(subtip)) {
                        return new EvenimentCultural(
                                rs.getInt("e.id"),
                                tip,
                                nume,
                                locatie,
                                data,
                                rs.getInt("e.durata"),
                                rs.getString("e.limba")
                        );
                    } else {
                        return new Eveniment(
                                rs.getInt("e.id"),
                                tip,
                                nume,
                                locatie,
                                data
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Eveniment eveniment) {
        String sql = "UPDATE Eveniment SET nume = ?, tip = ?, subtip = ?, data = ?, id_locatie = ?, echipa1 = ?, echipa2 = ?, esteDerby = ?, durata = ?, limba = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eveniment.getNume());
            stmt.setString(2, eveniment.getTip().name());

            if (eveniment instanceof EvenimentSportiv evSport) {
                stmt.setString(3, "SPORTIV");
                stmt.setTimestamp(4, Timestamp.valueOf(evSport.getData()));
                stmt.setInt(5, evSport.getLocatie().getId());
                stmt.setString(6, evSport.getEchipa1());
                stmt.setString(7, evSport.getEchipa2());
                stmt.setBoolean(8, evSport.isEsteDerby());
                stmt.setNull(9, Types.INTEGER);
                stmt.setNull(10, Types.VARCHAR);
            } else if (eveniment instanceof EvenimentCultural evCult) {
                stmt.setString(3, "CULTURAL");
                stmt.setTimestamp(4, Timestamp.valueOf(evCult.getData()));
                stmt.setInt(5, evCult.getLocatie().getId());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.BOOLEAN);
                stmt.setInt(9, evCult.getDurata());
                stmt.setString(10, evCult.getLimba());
            } else {
                stmt.setString(3, null);
                stmt.setTimestamp(4, Timestamp.valueOf(eveniment.getData()));
                stmt.setInt(5, eveniment.getLocatie().getId());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.BOOLEAN);
                stmt.setNull(9, Types.INTEGER);
                stmt.setNull(10, Types.VARCHAR);
            }

            stmt.setInt(11, eveniment.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Eveniment WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Eveniment> getEvenimenteDupaTip(TipEveniment tip) {
        List<Eveniment> lista = new ArrayList<>();
        String sql = "SELECT * FROM Eveniment e JOIN Locatie l ON e.id_locatie = l.id WHERE e.tip = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tip.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Locatie locatie = new Locatie(
                            rs.getInt("l.id"),
                            rs.getString("l.nume"),
                            rs.getString("l.adresa"),
                            rs.getInt("l.capacitate")
                    );
                    String subtip = rs.getString("e.subtip");
                    String nume = rs.getString("e.nume");
                    LocalDateTime data = rs.getTimestamp("e.data").toLocalDateTime();

                    if ("SPORTIV".equalsIgnoreCase(subtip)) {
                        lista.add(new EvenimentSportiv(
                                rs.getInt("e.id"),
                                nume,
                                locatie,
                                data,
                                rs.getString("e.echipa1"),
                                rs.getString("e.echipa2"),
                                rs.getBoolean("e.esteDerby")
                        ));
                    } else if ("CULTURAL".equalsIgnoreCase(subtip)) {
                        lista.add(new EvenimentCultural(
                                rs.getInt("e.id"),
                                tip,
                                nume,
                                locatie,
                                data,
                                rs.getInt("e.durata"),
                                rs.getString("e.limba")
                        ));
                    } else {
                        lista.add(new Eveniment(
                                rs.getInt("e.id"),
                                tip,
                                nume,
                                locatie,
                                data
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Eveniment> getEvenimenteViitoare() {
        List<Eveniment> lista = new ArrayList<>();
        String sql = "SELECT * FROM Eveniment e JOIN Locatie l ON e.id_locatie = l.id WHERE e.data > NOW()";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Locatie locatie = new Locatie(
                        rs.getInt("l.id"),
                        rs.getString("l.nume"),
                        rs.getString("l.adresa"),
                        rs.getInt("l.capacitate")
                );
                String tipStr = rs.getString("e.tip");
                TipEveniment tip = TipEveniment.valueOf(tipStr);
                String subtip = rs.getString("e.subtip");
                String nume = rs.getString("e.nume");
                LocalDateTime data = rs.getTimestamp("e.data").toLocalDateTime();

                if ("SPORTIV".equalsIgnoreCase(subtip)) {
                    lista.add(new EvenimentSportiv(
                            rs.getInt("e.id"),
                            nume,
                            locatie,
                            data,
                            rs.getString("e.echipa1"),
                            rs.getString("e.echipa2"),
                            rs.getBoolean("e.esteDerby")
                    ));
                } else if ("CULTURAL".equalsIgnoreCase(subtip)) {
                    lista.add(new EvenimentCultural(
                            rs.getInt("e.id"),
                            tip,
                            nume,
                            locatie,
                            data,
                            rs.getInt("e.durata"),
                            rs.getString("e.limba")
                    ));
                } else
                    lista.add(new Eveniment(
                            rs.getInt("e.id"), tip, nume, locatie, data));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Eveniment> readAll() {
        List<Eveniment> lista = new ArrayList<>();
        String sql = "SELECT * FROM Eveniment e JOIN Locatie l ON e.id_locatie = l.id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Locatie locatie = new Locatie(
                        rs.getInt("l.id"),
                        rs.getString("l.nume"),
                        rs.getString("l.adresa"),
                        rs.getInt("l.capacitate")
                );

                String subtip = rs.getString("e.subtip");
                String nume = rs.getString("e.nume");
                LocalDateTime data = rs.getTimestamp("e.data").toLocalDateTime();
                TipEveniment tip = TipEveniment.valueOf(rs.getString("e.tip"));

                Eveniment ev;
                if ("SPORTIV".equalsIgnoreCase(subtip)) {
                    ev = new EvenimentSportiv(
                            rs.getInt("e.id"), nume, locatie, data,
                            rs.getString("e.echipa1"),
                            rs.getString("e.echipa2"),
                            rs.getBoolean("e.esteDerby")
                    );
                } else if ("CULTURAL".equalsIgnoreCase(subtip)) {
                    ev = new EvenimentCultural(
                            rs.getInt("e.id"), tip, nume, locatie, data,
                            rs.getInt("e.durata"),
                            rs.getString("e.limba")
                    );
                } else {
                    ev = new Eveniment(
                            rs.getInt("e.id"), tip, nume, locatie, data
                    );
                }

                lista.add(ev);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


}
