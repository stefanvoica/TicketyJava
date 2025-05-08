package Servicii;

import Entitati.Locatie;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ServiciuLocatieImplementation implements ServiciuLocatie {
    private List<Locatie> locatii;

    public ServiciuLocatieImplementation() {
        this.locatii = new ArrayList<>();
    }

    @Override
    public void adaugaLocatie(Locatie locatie) {
        locatii.add(locatie);
        System.out.println("Locația a fost adăugată: " + locatie.getNume());
    }

    @Override
    public void stergeLocatie(String numeLocatie) {
        for (Locatie locatie : locatii) {
            if (locatie.getNume().equals(numeLocatie)) {
                locatii.remove(locatie);
                System.out.println("Locația a fost ștearsă: " + numeLocatie);
                return;
            }
        }
        System.out.println("Locația cu numele " + numeLocatie + " nu a fost găsită.");
    }

    @Override
    public Locatie cautaLocatie(String numeLocatie) {
        for (Locatie locatie : locatii) {
            if (locatie.getNume().equals(numeLocatie)) {
                return locatie;
            }
        }
        throw new NoSuchElementException("Locația cu numele '" + numeLocatie + "' nu a fost găsită.");
    }

    @Override
    public void modificaLocatie(String numeLocatie, Locatie locatieNoua) {
        for (int i = 0; i < locatii.size(); i++) {
            if (locatii.get(i).getNume().equals(numeLocatie)) {
                locatii.set(i, locatieNoua);
                System.out.println("Locația a fost modificată: " + locatieNoua.getNume());
                return;
            }
        }
        System.out.println("Locația cu numele " + numeLocatie + " nu a fost găsită.");
    }
}
