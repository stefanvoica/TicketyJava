package Servicii;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;

import java.util.List;

public interface ServiciuBilet {
    void cumparaBilet(int idClient, int idEveniment, String[] locuriStr, double pret);
    void stergeBilet(int idBilet);
    Bilet getBiletDupaId(int id);
    List<Bilet> getToateBiletele();
    List<Bilet> getBiletePentruClient(int idClient);
    boolean valideazaBilet(int idBilet);
}
