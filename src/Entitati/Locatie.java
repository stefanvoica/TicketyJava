package Entitati;

public class Locatie {
    private int id;
    private String nume;
    private String adresa;
    private int capacitate;

    public Locatie(int id, String nume, String adresa, int capacitate) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.capacitate = capacitate;
    }

    public Locatie(String nume, String adresa, int capacitate) {
        this.nume = nume;
        this.adresa = adresa;
        this.capacitate = capacitate;
    }

    public int getId() {
        return id;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getNume() {
        return nume;
    }

    public int getCapacitate() {
        return capacitate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", capacitate=" + capacitate +
                '}';
    }
}
