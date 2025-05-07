package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.util.List;
import java.util.Set;

public interface ServiciuClient {

    // Cumpără unul sau mai multe locuri la un eveniment
    Bilet cumparaBilet(Client client, Eveniment eveniment, Set<Integer> locuriDorite);

    // Returnează lista de bilete ale clientului
    List<Bilet> getBileteClient(Client client);

    // Validează un bilet (ex: la intrare) si returneaza daca s-a reusit
    boolean valideazaBilet(Bilet bilet);
}
