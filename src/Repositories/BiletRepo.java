package Repositories;

import Entitati.Bilet;

import java.util.ArrayList;
import java.util.List;

public class BiletRepo {
    private static List<Bilet> bilete = new ArrayList<>();

    public static void adaugaBilet(Bilet bilet) {
        bilete.add(bilet);
    }

    public static List<Bilet> getBilete() {
        return bilete;
    }

    public static void sorteazaBilete() {
        bilete.sort(null);
    }

    public static void afiseazaBilete() {
        for (Bilet b : bilete) {
            System.out.println(b);
        }
    }
}
