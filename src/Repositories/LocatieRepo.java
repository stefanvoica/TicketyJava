package Repositories;

import Entitati.Locatie;

import java.util.ArrayList;
import java.util.List;

public class LocatieRepo {
    private static List<Locatie> locatii = new ArrayList<>();

    public static void adaugaLocatie(Locatie locatie) {
        locatii.add(locatie);
    }

    public static void stergeLocatie(Locatie locatie) {
        locatii.remove(locatie);
    }

    public static List<Locatie> getLocatii() {
        return new ArrayList<>(locatii);
    }

    public static void afiseazaLocatii() {
        if (locatii.isEmpty()) {
            System.out.println("Nu există locații înregistrate.");
        } else {
            System.out.println("Lista locațiilor:");
            for (Locatie locatie : locatii) {
                System.out.println(locatie);
            }
        }
    }

    public static void stergeToate() {
        locatii.clear();
    }
}
