package Servicii;

import DAO.BiletDAO;
import DAO.ClientDAO;
import Entitati.Bilet;
import Entitati.Client;

import java.util.List;

public class ServiciuClientImplementation implements ServiciuClient {
    private static ServiciuClientImplementation instance = null;
    private final ClientDAO clientDAO = ClientDAO.getInstance();
    private final BiletDAO biletDAO = BiletDAO.getInstance();
    private final ServiciuAudit audit = ServiciuAudit.getInstanta();

    private ServiciuClientImplementation() {}

    public static ServiciuClientImplementation getInstance() {
        if (instance == null) instance = new ServiciuClientImplementation();
        return instance;
    }

    @Override
    public Client adaugaClient(String nume, String email, int varsta, String adresa) {
        Client client = new Client(nume, email, varsta, adresa);
        clientDAO.create(client);
        audit.scrieActiune("adauga_client");
        System.out.println("Client creat cu succes: " + client);
        return client;
    }

    @Override
    public List<Bilet> getBileteClient(Client client) {
        audit.scrieActiune("citeste_bilete_client");
        return biletDAO.readByClientId(client.getId());
    }



    public void actualizeazaClient(int id, String nume, String email, int varsta, String adresa) {
        Client c = clientDAO.read(id);
        c.setNume(nume);
        c.setEmail(email);
        c.setVarsta(varsta);
        c.setAdresa(adresa);
        clientDAO.update(c);
        audit.scrieActiune("actualizeaza_client");
        System.out.println("Client actualizat.");
    }

    public void stergeClient(int id) {
        clientDAO.delete(id);
        audit.scrieActiune("sterge_client");
        System.out.println("Client șters.");
    }

    public Client getClientDupaId(int id) {
        audit.scrieActiune("citeste_client_dupa_id");
        return clientDAO.read(id);
    }

    public List<Client> getTotiClientii() {
        audit.scrieActiune("citeste_totii_clientii");
        return clientDAO.readAll();
    }
}
