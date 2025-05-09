package Servicii;

import Entitati.Locatie;
import Repositories.LocatieRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ServiciuLocatieImplementation implements ServiciuLocatie {
    @Override
    public void adaugaLocatie(Locatie locatie) {
        LocatieRepo.adaugaLocatie(locatie);
        System.out.println("Locația a fost adăugată: " + locatie.getNume());
    }

    @Override
    public void stergeLocatie(String numeLocatie) {
        for (Locatie locatie : LocatieRepo.getLocatii()) {
            if (locatie.getNume().equals(numeLocatie)) {
                LocatieRepo.stergeLocatie(locatie);
                System.out.println("Locația a fost ștearsă: " + numeLocatie);
                return;
            }
        }
        System.out.println("Locația cu numele " + numeLocatie + " nu a fost găsită.");
    }

    @Override
    public Locatie cautaLocatie(String numeLocatie) {
        for (Locatie locatie : LocatieRepo.getLocatii()) {
            if (locatie.getNume().equals(numeLocatie)) {
                return locatie;
            }
        }
        throw new NoSuchElementException("Locația cu numele '" + numeLocatie + "' nu a fost găsită.");
    }

    @Override
    public void modificaLocatie(String numeLocatie, Locatie locatieNoua) {
        List<Locatie> lista = LocatieRepo.getLocatii();
        ListIterator<Locatie> iterator = lista.listIterator();

        while (iterator.hasNext()) {
            Locatie locatie = iterator.next();
            if (locatie.getNume().equals(numeLocatie)) {
                iterator.set(locatieNoua);
                System.out.println("Locația a fost modificată: " + locatieNoua.getNume());
                return;
            }
        }

        System.out.println("Locația cu numele " + numeLocatie + " nu a fost găsită.");
    }
}
