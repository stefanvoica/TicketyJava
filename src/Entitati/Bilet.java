package Entitati;

import Utile.Status;

import java.util.Set;

import static Utile.ClientUtils.idCurent;

public class Bilet {
    private final int idBilet;
    private Eveniment eveniment;
    private Set<Integer> locuri; //lista cu locuri rezervate
    private double pret;
    private Status validat;

    public Bilet(Eveniment eveniment, Set<Integer> locuri, double pret) {
        this.idBilet = idCurent;
        idCurent++;
        this.eveniment = eveniment;
        this.locuri = locuri;
        this.pret = pret;
        this.validat = Status.NEVALIDAT;
    }

    public void setValidat(Status validat) {
        this.validat = validat;
    }

    public Status isValidat() {
        return validat;
    }
}
