package Entitati;

import Utile.TipEveniment;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Eveniment {
    private TipEveniment tip;
    private String nume;
    private Locatie locatie;
    private LocalDateTime data;
    private Set<Integer> locuriOcupate; //In momentul in care se adauga un bilet nou, se incearca adaugarea locurilor noi

    public Eveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data) {
        this.tip = tip;
        this.nume = nume;
        this.locatie = locatie;
        this.data = data;
        this.locuriOcupate = new HashSet<>();;
    }

    ///repet logica cu throw...
    public void rezervaLocuri(Set<Integer> locuriDorite) {
        locuriOcupate.addAll(locuriDorite);
    }


    public Set<Integer> getLocuriOcupate() {
        return locuriOcupate;
    }
}
