import Entitati.*;
import Repositories.BiletRepo;
import Repositories.ClientRepo;
import Repositories.EvenimentRepo;
import Servicii.*;
import Utile.TipEveniment;

import java.time.LocalDateTime;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ServiciuLocatie serviciuLocatie = new ServiciuLocatieImplementation();
        Locatie locatie1 = new Locatie("Arena Națională", "Bd. Basarabia, București", 200);
        Locatie locatie2 = new Locatie("Stadionul Ghencea", "Bd. Ghencea, București", 120);
        Locatie locatie3 = new Locatie("Teatrul Național","Bd. N. Bălcescu, București",100);
        serviciuLocatie.adaugaLocatie(locatie1);
        serviciuLocatie.adaugaLocatie(locatie2);
        serviciuLocatie.adaugaLocatie(locatie3);
        try {
            Locatie locatieGasita = serviciuLocatie.cautaLocatie("Steaua");
            serviciuLocatie.stergeLocatie(locatieGasita.getNume());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        ServiciuEveniment serviciuEveniment = new ServiciuEvenimentImplementation();
        Eveniment ev1 = serviciuEveniment.adaugaEvenimentSportiv("FCSB vs Rapid", locatie1, LocalDateTime.of(2025, 3, 31, 20, 45), "FCSB", "Rapid", true);
        Eveniment ev2 = serviciuEveniment.adaugaEvenimentCultural(TipEveniment.TEATRU, "O scrisoare pierdută", locatie3, LocalDateTime.of(2025, 4, 23, 16, 30), 95, "română");
        Eveniment ev3 = serviciuEveniment.adaugaEvenimentCultural(TipEveniment.CONCERT, "Ed Sheeran", locatie1, LocalDateTime.of(2025, 6, 15, 22, 0), 120, "engleză");
        Eveniment ev4 = serviciuEveniment.adaugaEvenimentSportiv("Dinamo vs U Craiova", locatie2, LocalDateTime.of(2025, 5, 9, 21, 15), "Dinamo", "U Craiova", true);
        Eveniment ev5 = serviciuEveniment.adaugaEvenimentCultural(TipEveniment.FILM, "Titanic", locatie3, LocalDateTime.of(2025, 4, 30, 19, 30), 180, "engleză");

        serviciuEveniment.actualizeazaEveniment(ev3, "Ed Sheeran LIVE", locatie1, LocalDateTime.of(2025, 6, 15, 21, 30));

        serviciuEveniment.stergeEveniment(ev5);

        System.out.println("\nAfisam evenimentele dupa tipul TEATRU:");
        List<Eveniment> sporturi = serviciuEveniment.getEvenimenteDupaTip(TipEveniment.TEATRU);
        sporturi.forEach(System.out::println);

        System.out.println("\nAfisam evenimentele viitoare:");
        List<Eveniment> viitoare = serviciuEveniment.getEvenimenteViitoare();
        viitoare.forEach(System.out::println);

        System.out.println("\nEvenimente Sportive:");
        for (Eveniment ev : EvenimentRepo.getEvenimente()) {
            if (ev instanceof EvenimentSportiv) {
                System.out.println(ev);
            }
        }

        System.out.println("\nEvenimente Culturale:");
        for (Eveniment ev : EvenimentRepo.getEvenimente()) {
            if (ev instanceof EvenimentCultural) {
                System.out.println(ev);
            }
        }

        System.out.println("\nRezervări:");
        serviciuEveniment.rezervaLocuri(ev1, Set.of(1, 2, 3));
        System.out.println("Locuri ocupate la ev1: " + ev1.getLocuriOcupate());

        //============================================================
        ServiciuClient serviciuClient = new ServiciuClientImplementation();

        Client client1 = serviciuClient.creazaClient("Maria Ionescu", "maria.ionescu@email.com", 30, "Bd. Unirii 15");
        Client client2 = serviciuClient.creazaClient("Ion Popescu", "ion.popescu@email.com", 25, "Str. Libertatii 10");
        Client client3 = serviciuClient.creazaClient("Alex Vasile", "alex.vasile@email.com", 22, "Aleea Parcului 5");
        Client client4 = serviciuClient.creazaClient("Elena Marin", "elena.marin@email.com", 28, "Str. Victoriei 20");


        //============================================================
        ServiciuBilet serviciuBilet = new ServiciuBiletImplementation();
        Bilet bilet1 = serviciuBilet.cumparaBilet(client1, ev1, Set.of(10, 11));
        Bilet bilet2 = serviciuBilet.cumparaBilet(client2, ev2, Set.of(99, 52, 98));
        try {
            Bilet bilet3 = serviciuBilet.cumparaBilet(client3, ev3, Set.of(200, 201,188));
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare la cumpărare bilet: " + e.getMessage());
        }
        Bilet bilet33 = serviciuBilet.cumparaBilet(client3, ev3, Set.of(200, 52, 102, 85, 104,188));
        Bilet bilet4 = serviciuBilet.cumparaBilet(client4, ev4, Set.of(10, 11, 111, 120, 32, 19));
        Bilet bilet5 = serviciuBilet.cumparaBilet(client1, ev2, Set.of(7, 5, 6, 8, 16));
        try {
            Bilet bilet6 = serviciuBilet.cumparaBilet(client3, ev2, Set.of(7, 100, 5));
        } catch (IllegalArgumentException e) {
            System.out.println("Eroare la cumpărare bilet: " + e.getMessage());
        }

        boolean valid = serviciuBilet.valideazaBilet(bilet1);
        System.out.println("Bilet validat: " + valid);


        // Obținem lista de bilete ale unor clienți
        System.out.println("Biletele cumpărate de " + client1.getNume() + ": " + client1.getBilete());
        System.out.println("Biletele cumpărate de " + client2.getNume() + ": " + client2.getBilete());

        // Afisam lista initala a clientilor
        //System.out.println(ClientRepo.getClienti());
        System.out.println("\nLista clienților:");
        ClientRepo.afiseazaClienti();

        // Lista sortată a clienților
        ClientRepo.sorteazaClienti();
        System.out.println("\nLista clienților sortată după nr. bilete (desc), nume(crescător):");
        ClientRepo.afiseazaClienti();


        // Afisam lista initiala a biletelor
        System.out.println("\nLista initala a biletelor");
        BiletRepo.afiseazaBilete();

        // Sortam si afisam lista biletelor
        BiletRepo.sorteazaBilete();
        System.out.println("\nLista biletelor sortată după pret):");
        BiletRepo.afiseazaBilete();

        Map<TipEveniment, Integer> statisticaEvenimente = new HashMap<>();

        for (Eveniment ev : Repositories.EvenimentRepo.getEvenimente()) {
            TipEveniment tip = ev.getTip();
            statisticaEvenimente.put(tip, statisticaEvenimente.getOrDefault(tip, 0) + 1);
        }

        System.out.println("\nNumăr de evenimente pe fiecare tip:");
        for (Map.Entry<TipEveniment, Integer> entry : statisticaEvenimente.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}