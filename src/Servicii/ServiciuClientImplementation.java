package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Repositories.ClientRepo;
import Utile.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ServiciuClientImplementation implements ServiciuClient {
    @Override
    public Client creazaClient(String nume, String email, int varsta, String adresa) {
        Client client = new Client(nume, email, varsta, adresa);
        //clienti.add(client);
        ClientRepo.adaugaClient(client);
        //bileteClient.put(client, new ArrayList<>());
        System.out.println("Client creat cu succes: " + client);
        return client;
    }

    @Override
    public List<Bilet> getBileteClient(Client client) {
        return client.getBilete();
    }
}
