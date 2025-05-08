package Servicii;

import Entitati.Locatie;

public interface ServiciuLocatie {
    void adaugaLocatie(Locatie locatie);
    void stergeLocatie(String numeLocatie);
    Locatie cautaLocatie(String numeLocatie);
    void modificaLocatie(String numeLocatie, Locatie locatieNoua);
}
