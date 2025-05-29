package Entitati;

import Utile.Status;

import java.util.Set;

import static Utile.ClientUtils.idCurent;

public class Bilet implements Comparable<Bilet> {
    private int id;
    private Eveniment eveniment;
    private Client client;
    private Set<Integer> locuri; //lista cu locuri rezervate
    private double pret;
    private Status validat;

    public Bilet(Eveniment eveniment, Client client, Set<Integer> locuri, double pret) {
        this.id = idCurent;
        idCurent++;
        this.eveniment = eveniment;
        this.client = client;
        this.locuri = locuri;
        this.pret = pret;
        this.validat = Status.NEVALIDAT;
    }

    public Bilet(int id, Eveniment eveniment, Client client, Set<Integer> locuri, double pret, Status validat) {
        this.id = id;
        this.eveniment = eveniment;
        this.client = client;
        this.locuri = locuri;
        this.pret = pret;
        this.validat = validat;
    }


    public void setValidat(Status validat) {
        this.validat = validat;
    }

    public double getPret() {
        return pret;
    }

    public Status isValidat() {
        return validat;
    }

    public int getId() {
        return id;
    }

    public Eveniment getEveniment() {
        return eveniment;
    }

    public Client getClient() {
        return client;
    }

    public Set<Integer> getLocuri() {
        return locuri;
    }

    public Status getValidat() {
        return validat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEveniment(Eveniment eveniment) {
        this.eveniment = eveniment;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLocuri(Set<Integer> locuri) {
        this.locuri = locuri;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "id=" + id +
                ", eveniment=" + eveniment +
                ", client=" + client.getNume() +
                ", locuri=" + locuri +
                ", pret=" + pret +
                ", validat=" + validat +
                '}';
    }

    @Override
    public int compareTo(Bilet altBilet) {
        return Double.compare(this.getPret(), altBilet.getPret());
    }
}
