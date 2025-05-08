package Entitati;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nume;
    private String email;
    private int varsta;
    private String adresa;
    private int numarBilete;
    private List<Bilet> bilete;

    public Client(String nume, String email, int varsta, String adresa) {
        this.nume = nume;
        this.email = email;
        this.varsta = varsta;
        this.adresa = adresa;
        this.numarBilete = 0;
        this.bilete = new ArrayList<>();
    }

    public List<Bilet> getBilete() {
        return bilete;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", numarBilete=" + numarBilete +
                ", bilete=" + bilete +
                '}';
    }
}
