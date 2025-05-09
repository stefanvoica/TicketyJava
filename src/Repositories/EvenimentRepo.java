package Repositories;

import Entitati.Eveniment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvenimentRepo {
    private static final List<Eveniment> evenimente = new ArrayList<>();

    public static void adaugaEveniment(Eveniment eveniment) {
        evenimente.add(eveniment);
    }

    public static void stergeEveniment(Eveniment eveniment) {
        evenimente.remove(eveniment);
    }

    public static List<Eveniment> getEvenimente() {
        return Collections.unmodifiableList(evenimente);
    }

    public static void afiseazaEvenimente() {
        evenimente.forEach(System.out::println);
    }

    public static void golesteLista() {
        evenimente.clear();
    }
}
