package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Repositories.BiletRepo;
import Utile.Status;

import java.util.HashSet;
import java.util.Set;

public class ServiciuBiletImplementation implements ServiciuBilet {
    @Override
    public Bilet cumparaBilet(Client client, Eveniment eveniment, Set<Integer> locuriDorite) {
        // Verificăm dacă locurile sunt disponibile
        Set<Integer> locuriDisponibile = eveniment.getLocuriLibere();
        if (!locuriDisponibile.containsAll(locuriDorite)) {
            throw new IllegalArgumentException("Unele locuri dorite nu sunt disponibile.");
        }

        eveniment.rezervaLocuri(locuriDorite);

        double pret = locuriDorite.size() * 50.1;
        Bilet bilet = new Bilet(eveniment, client, locuriDorite, pret);

        client.adaugaBilet(bilet);

        System.out.println("Bilet cumparat cu succes: " + bilet);

        BiletRepo.adaugaBilet(bilet);
        return bilet;
    }

    @Override
    public boolean valideazaBilet(Bilet bilet) {
        if (bilet.isValidat() == Status.VALIDAT) {
            return false; // deja validat
        }

        bilet.setValidat(Status.VALIDAT);
        return true;
    }
}
