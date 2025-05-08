package Entitati;

public class Locatie {
    private String nume;
    private String adresa;
    private int capacitate;

    public Locatie(String nume, String adresa, int capacitate) {
        this.nume = nume;
        this.adresa = adresa;
        this.capacitate = capacitate;
    }

    public String getNume() {
        return nume;
    }

    public int getCapacitate() {
        return capacitate;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", capacitate=" + capacitate +
                '}';
    }
}
