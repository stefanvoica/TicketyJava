package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.util.List;
import java.util.Set;

public interface ServiciuClient {
    // Creează și înregistrează un nou client
    Client creazaClient(String nume, String email, int varsta, String adresa);

    // Returnează lista de bilete ale clientului
    List<Bilet> getBileteClient(Client client);
}
