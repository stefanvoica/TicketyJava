package Entitati;

import Utile.TipEveniment;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Eveniment {
    private int id;
    private TipEveniment tip;
    private String nume;
    private Locatie locatie;
    private LocalDateTime data;
    private Set<Integer> locuriOcupate; //In momentul in care se adauga un bilet nou, se incearca adaugarea locurilor noi

    public Eveniment(int id, TipEveniment tip, String nume, Locatie locatie, LocalDateTime data) {
        this.id = id;
        this.tip = tip;
        this.nume = nume;
        this.locatie = locatie;
        this.data = data;
    }

    public Eveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data) {
        this.tip = tip;
        this.nume = nume;
        this.locatie = locatie;
        this.data = data;
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

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTip(TipEveniment tip) {
        this.tip = tip;
    }

    public void setLocuriOcupate(Set<Integer> locuriOcupate) {
        this.locuriOcupate = locuriOcupate;
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
                "id=" + id +
                ", tip=" + tip +
                ", nume='" + nume + '\'' +
                ", locatie=" + locatie +
                ", data=" + data +
                ", locuriOcupate=" + locuriOcupate +
                '}';
    }
}
