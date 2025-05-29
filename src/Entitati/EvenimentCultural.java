package Entitati;

import Utile.TipEveniment;

import java.time.LocalDateTime;

public class EvenimentCultural extends Eveniment{
    private int durata;
    private String limba;

    public EvenimentCultural(int id, TipEveniment tip, String nume, Locatie locatie, LocalDateTime data,
                             int durata, String limba) {
        super(id, tip, nume, locatie, data);
        this.durata = durata;
        this.limba = limba;
    }

    public EvenimentCultural(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data,
                             int durata, String limba) {
        super(tip, nume, locatie, data);
        this.durata = durata;
        this.limba = limba;
    }

    public int getDurata() {
        return durata;
    }

    public String getLimba() {
        return limba;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public void setLimba(String limba) {
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
