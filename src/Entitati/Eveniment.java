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
        this.locuriOcupate = new HashSet<>();
    }

    public LocalDateTime getData() {
        return data;
    }

    public TipEveniment getTip() {
        return tip;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setLocatie(Locatie locatie) {
        this.locatie = locatie;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void rezervaLocuri(Set<Integer> locuriDorite) {
        Set<Integer> locuriDisponibile = getLocuriLibere();

        if (!locuriDisponibile.containsAll(locuriDorite)) {
            throw new IllegalArgumentException("Unele locuri dorite sunt deja ocupate.");
        }

        locuriOcupate.addAll(locuriDorite);
    }



    public Set<Integer> getLocuriOcupate() {
        return locuriOcupate;
    }

    public Set<Integer> getLocuriLibere() {
        Set<Integer> toateLocurile = new HashSet<>();
        for (int i = 1; i <= locatie.getCapacitate(); i++) {
            toateLocurile.add(i);
        }
        toateLocurile.removeAll(locuriOcupate);
        return toateLocurile;
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "tip=" + tip +
                ", nume='" + nume + '\'' +
                ", locatie=" + locatie +
                ", data=" + data +
                ", locuriOcupate=" + locuriOcupate +
                '}';
    }
}
