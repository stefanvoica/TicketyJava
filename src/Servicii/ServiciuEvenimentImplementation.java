package Servicii;

import Entitati.Eveniment;
import Entitati.EvenimentCultural;
import Entitati.EvenimentSportiv;
import Entitati.Locatie;
import Repositories.EvenimentRepo;
import Utile.TipEveniment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ServiciuEvenimentImplementation implements ServiciuEveniment {
    @Override
    public Eveniment adaugaEveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data) {
        Eveniment eveniment = new Eveniment(tip, nume, locatie, data);
        EvenimentRepo.adaugaEveniment(eveniment);

        System.out.println("Eveniment adăugat cu succes: " + eveniment.toString());

        return eveniment;
    }

    @Override
    public Eveniment adaugaEvenimentCultural(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data, int durata, String limba) {
        EvenimentCultural ec = new EvenimentCultural(tip, nume, locatie, data, durata, limba);
        EvenimentRepo.adaugaEveniment(ec);
        System.out.println("Eveniment adăugat cu succes: " + ec.toString());

        return ec;
    }

    @Override
    public Eveniment adaugaEvenimentSportiv(String nume, Locatie locatie, LocalDateTime data, String echipa1, String echipa2, boolean esteDerby) {
        EvenimentSportiv es = new EvenimentSportiv(nume, locatie, data, echipa1, echipa2, esteDerby);
        EvenimentRepo.adaugaEveniment(es);
        System.out.println("Eveniment adăugat cu succes: " + es.toString());

        return es;
    }


    @Override
    public void stergeEveniment(Eveniment eveniment) {
        System.out.println("Eveniment șters: " + eveniment.toString());
        EvenimentRepo.stergeEveniment(eveniment);
    }

    @Override
    public void actualizeazaEveniment(Eveniment eveniment, String numeNou, Locatie locatieNoua, LocalDateTime dataNoua) {
        // presupun că modificăm direct referința
        eveniment.setNume(numeNou);
        eveniment.setLocatie(locatieNoua);
        eveniment.setData(dataNoua);

        System.out.println("S-a actualizat cu succes: " + eveniment.toString());
    }

    @Override
    public List<Eveniment> getEvenimenteDupaTip(TipEveniment tip) {
        return EvenimentRepo.getEvenimente().stream()
                .filter(e -> e.getTip().equals(tip))
                .collect(Collectors.toList());
    }


    @Override
    public List<Eveniment> getEvenimenteViitoare() {
        LocalDateTime acum = LocalDateTime.now();
        return EvenimentRepo.getEvenimente().stream()
                .filter(e -> e.getData().isAfter(acum))
                .collect(Collectors.toList());
    }

    @Override
    public void rezervaLocuri(Eveniment eveniment, Set<Integer> locuri) {
        Set<Integer> locuriLibere = eveniment.getLocuriLibere();
        if (!locuriLibere.containsAll(locuri)) {
            throw new IllegalArgumentException("Unele locuri sunt deja ocupate sau invalide!");
        }
        eveniment.rezervaLocuri(locuri);
        System.out.printf("S-au rezervat cu succes locurile %s la eveniment %s%n", locuri.toString(), eveniment.toString());
    }

    @Override
    public void elibereazaLocuri(Eveniment eveniment, Set<Integer> locuri) {
        eveniment.getLocuriOcupate().removeAll(locuri);
        System.out.printf("S-au eliberat cu succes locurile %s la eveniment %s%n", locuri.toString(), eveniment.toString());

    }
}
