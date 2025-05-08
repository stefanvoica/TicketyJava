package Entitati;

import Utile.TipEveniment;

import java.time.LocalDateTime;

public class EvenimentSportiv extends Eveniment {
    private String echipa1;
    private String echipa2;
    private boolean esteDerby;

    public EvenimentSportiv(String nume, Locatie locatie, LocalDateTime data,
                   String echipa1, String echipa2, boolean esteDerby) {
        super(TipEveniment.SPORT, nume, locatie, data);
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
        this.esteDerby = esteDerby;
    }

    @Override
    public String toString() {
        return "EvenimentSportiv{" + super.toString() +
                "echipa1='" + echipa1 + '\'' +
                ", echipa2='" + echipa2 + '\'' +
                ", esteDerby=" + esteDerby +
                '}';
    }
}
