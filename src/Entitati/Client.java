package Entitati;

import java.util.ArrayList;
import java.util.List;

public class Client implements Comparable<Client> {
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

    public void adaugaBilet(Bilet bilet) {
        try {
            this.bilete.add(bilet);  // Adaugă biletul la lista de bilete
            numarBilete++;  // Actualizează numărul de bilete
        } catch (Exception e) {
            System.out.println("Eroare la adăugarea biletului: " + e.getMessage());
        }
    }


    public List<Bilet> getBilete() {
        return bilete;
    }

    public String getNume() {
        return nume;
    }

    public String getEmail() {
        return email;
    }

    private int getNumarBilete() {
        return numarBilete;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", numarBilete=" + numarBilete +
                '}';
    }

    @Override
    public int compareTo(Client altClient) {
        int nrBileteComparison = Integer.compare(altClient.getNumarBilete(), this.getNumarBilete());

        if (nrBileteComparison == 0)
            return this.getNume().compareTo(altClient.getNume());
        return nrBileteComparison;
    }
}
