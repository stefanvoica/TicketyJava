package Servicii;

import Entitati.Locatie;

import java.util.List;

public interface ServiciuLocatie {
    void adaugaLocatie(String nume, String adresa, int capacitate);
    void stergeLocatie(int id);
    Locatie getLocatieDupaId(int id);
    void actualizeazaLocatie(int id, String nume, String adresa, int capacitate);
    List<Locatie> getToateLocatiile();
}
