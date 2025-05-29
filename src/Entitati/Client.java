package Entitati;

import java.util.ArrayList;
import java.util.List;

public class Client implements Comparable<Client> {
    private int id;
    private String nume;
    private String email;
    private int varsta;
    private String adresa;
    private List<Bilet> bilete;

    public Client(int id, String nume, String email, int varsta, String adresa, List<Bilet> bilete) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.varsta = varsta;
        this.adresa = adresa;
        this.bilete = bilete;
    }

    public Client(String nume, String email, int varsta, String adresa) {
        this.nume = nume;
        this.email = email;
        this.varsta = varsta;
        this.adresa = adresa;
        this.bilete = new ArrayList<>();
    }

    public void adaugaBilet(Bilet bilet) {
        try {
            this.bilete.add(bilet);
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

    public int getId() {
        return id;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


    public void setBilete(List<Bilet> bilete) {
        this.bilete = bilete;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", varsta=" + varsta +
                ", adresa='" + adresa + '\'' +
                ", bilete=" + bilete +
                '}';
    }

    public int getNumarBilete() {
        return bilete.size();
    }

    @Override
    public int compareTo(Client altClient) {
        int nrBileteComparison = Integer.compare(altClient.getNumarBilete(), this.getNumarBilete());

        if (nrBileteComparison == 0)
            return this.getNume().compareTo(altClient.getNume());
        return nrBileteComparison;
    }
}
