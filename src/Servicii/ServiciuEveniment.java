package Servicii;

import Entitati.Eveniment;
import Entitati.Locatie;
import Utile.TipEveniment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ServiciuEveniment {
    Eveniment adaugaEveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data);
    Eveniment adaugaEvenimentCultural(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data, int durata, String limba);
    Eveniment adaugaEvenimentSportiv(String nume, Locatie locatie, LocalDateTime data, String echipa1, String echipa2, boolean esteDerby);

    //void actualizeazaEveniment(Eveniment eveniment, String numeNou, Locatie locatieNoua, LocalDateTime dataNoua);

    List<Eveniment> getEvenimenteDupaTip(TipEveniment tip);
    List<Eveniment> getEvenimenteViitoare(); // Filtrare după data curentă
    List<Eveniment> getToateEvenimentele();
    Eveniment getEvenimentDupaId(int id);

    void rezervaLocuri(Eveniment eveniment, Set<Integer> locuri);
    void elibereazaLocuri(Eveniment eveniment, Set<Integer> locuri);

    void stergeEveniment(int id);

}