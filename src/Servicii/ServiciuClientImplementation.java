package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ServiciuClientImplementation implements ServiciuClient {
    private List<Client> clienti = new ArrayList<>();
    //private Map<Client, List<Bilet>> bileteClient = new HashMap<>();

    @Override
    public Client creazaClient(String nume, String email, int varsta, String adresa) {
        Client client = new Client(nume, email, varsta, adresa);
        clienti.add(client);
        //bileteClient.put(client, new ArrayList<>()); // inițializezi lista de bilete, dacă folosești asta
        System.out.println("Client creat cu succes: " + client);
        return client;
    }

    @Override
    public List<Bilet> getBileteClient(Client client) {
        return new ArrayList<>(client.getBilete());
    }
}
