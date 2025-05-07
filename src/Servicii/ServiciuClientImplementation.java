package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServiciuClientImplementation implements ServiciuClient {
    private Client client;

    public ServiciuClientImplementation(Client client) {
        this.client = client;
    }

    @Override
    public Bilet cumparaBilet(Client client, Eveniment eveniment, Set<Integer> locuriDorite) {
        Set<Integer> locuriOcupate = eveniment.getLocuriOcupate();

        for (Integer loc : locuriDorite) {
            if (locuriOcupate.contains(loc)) {
                throw new IllegalArgumentException("Locul " + loc + " este deja ocupat.");
            }
        }

        // Marchează locurile ca ocupate
        eveniment.rezervaLocuri(locuriDorite);

        // Prețul per loc – hardcodat, poate fi modificat
        double pretPerLoc = 50.1;
        double pretTotal = locuriDorite.size() * pretPerLoc;

        // Creează biletul
        Bilet bilet = new Bilet(eveniment, locuriDorite, pretTotal);

        // Adaugă biletul la client
        //client.getBileteCumparate().add(bilet);

        return bilet;
    }

    @Override
    public List<Bilet> getBileteClient(Client client) {
        return new ArrayList<>();
        //return new ArrayList<>(client.getBileteCumparate());
    }

    @Override
    public boolean valideazaBilet(Bilet bilet) {
        if (bilet.isValidat() == Status.NEVALIDAT) {
            bilet.setValidat(Status.VALIDAT);
            return true;
        }
        return false;
    }
}
