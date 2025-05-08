package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;

import java.util.Set;

public interface ServiciuBilet {
    // Cumpără unul sau mai multe locuri la un eveniment
    Bilet cumparaBilet(Client client, Eveniment eveniment, Set<Integer> locuriDorite);

    // Validează un bilet (ex: la intrare) si returneaza daca s-a reusit
    boolean valideazaBilet(Bilet bilet);
}
