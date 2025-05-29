package Servicii;

import Entitati.Bilet;
import Entitati.Client;

import java.util.List;

public interface ServiciuClient {
    Client adaugaClient(String nume, String email, int varsta, String adresa);
    List<Bilet> getBileteClient(Client client);

    void actualizeazaClient(int id, String nume, String email, int varsta, String adresa);
    void stergeClient(int id);
    Client getClientDupaId(int id);
    List<Client> getTotiClientii();
}
