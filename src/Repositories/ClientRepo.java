package Repositories;

import Entitati.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    private static List<Client> clienti = new ArrayList<>();

    public static void adaugaClient(Client client) {
        clienti.add(client);
    }

    public static List<Client> getClienti() {
        return clienti;
    }

    public static void sorteazaClienti() {
        clienti.sort(null);  // Folosesc compareTo din Client
    }

    public static void afiseazaClienti() {
        for (Client client : clienti) {
            System.out.println(client);
        }
    }
}
