import Entitati.*;
import Servicii.*;
import Utile.TipEveniment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

        ServiciuEveniment serviciuEveniment = new ServiciuEvenimentImplementation();
        Eveniment ev1 = serviciuEveniment.adaugaEveniment(TipEveniment.SPORT, "FCSB vs Rapid", locatie1, LocalDateTime.of(2025, 3, 31, 20, 45));
        Eveniment ev2 = serviciuEveniment.adaugaEveniment(TipEveniment.TEATRU, "O scrisoare pierdută", locatie3, LocalDateTime.of(2025, 4, 23, 16, 30));
        Eveniment ev3 = serviciuEveniment.adaugaEveniment(TipEveniment.CONCERT, "Ed Sheeran", locatie1, LocalDateTime.of(2025, 6, 15, 22, 0));
        Eveniment ev4 = serviciuEveniment.adaugaEveniment(TipEveniment.SPORT, "Dinamo vs U Craiova", locatie2, LocalDateTime.of(2025, 5, 9, 21, 15));
        Eveniment ev5 = serviciuEveniment.adaugaEveniment(TipEveniment.FILM, "Titanic", locatie3, LocalDateTime.of(2025, 4, 30, 19, 30));

        serviciuEveniment.actualizeazaEveniment(ev3, "Ed Sheeran LIVE", locatie1, LocalDateTime.of(2025, 6, 15, 21, 30));

        serviciuEveniment.stergeEveniment(ev5);

        List<Eveniment> sporturi = serviciuEveniment.getEvenimenteDupaTip(TipEveniment.SPORT);
        sporturi.forEach(System.out::println);

        List<Eveniment> viitoare = serviciuEveniment.getEvenimenteViitoare();
        viitoare.forEach(System.out::println);

        System.out.println("\nRezervări:");
        serviciuEveniment.rezervaLocuri(ev1, Set.of(1, 2, 3));
        System.out.println("Locuri ocupate la ev1: " + ev1.getLocuriOcupate());

        //============================================================
        ServiciuClient serviciuClient = new ServiciuClientImplementation();

        // Adaugi 4 clienți
        Client client1 = serviciuClient.creazaClient("Ion Popescu", "ion.popescu@email.com", 25, "Str. Libertatii 10");
        Client client2 = serviciuClient.creazaClient("Maria Ionescu", "maria.ionescu@email.com", 30, "Bd. Unirii 15");
        Client client3 = serviciuClient.creazaClient("Alex Vasile", "alex.vasile@email.com", 22, "Aleea Parcului 5");
        Client client4 = serviciuClient.creazaClient("Elena Marin", "elena.marin@email.com", 28, "Str. Victoriei 20");

        // Poți verifica lista de clienți (dacă ai o metodă pentru asta)
        System.out.println("Au fost adăugați 4 clienți:");
        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client3);
        System.out.println(client4);

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

    }
}