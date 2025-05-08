package Entitati;

import Utile.TipEveniment;

import java.time.LocalDateTime;

public class EvenimentCultural extends Eveniment{
    private int durata;
    private String limba;

    public EvenimentCultural(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data,
                             int durata, String limba) {
        super(tip, nume, locatie, data);
        this.durata = durata;
        this.limba = limba;
    }

    @Override
    public String toString() {
        return "EvenimentCultural{" + super.toString() +
                "durata=" + durata +
                ", limba='" + limba + '\'' +
                '}';
    }
}
