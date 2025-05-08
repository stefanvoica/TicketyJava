package Servicii;

import Entitati.Eveniment;
import Entitati.Locatie;
import Utile.TipEveniment;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ServiciuEveniment {
    Eveniment adaugaEveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data);
    void stergeEveniment(Eveniment eveniment);
    void actualizeazaEveniment(Eveniment eveniment, String numeNou, Locatie locatieNoua, LocalDateTime dataNoua);

    List<Eveniment> getEvenimenteDupaTip(TipEveniment tip);
    List<Eveniment> getEvenimenteViitoare(); // Filtrare după data curentă

    void rezervaLocuri(Eveniment eveniment, Set<Integer> locuri);
    void elibereazaLocuri(Eveniment eveniment, Set<Integer> locuri);
}