package Meniu;

import DAO.LocatieDAO;
import Entitati.Locatie;
import Servicii.ServiciuEveniment;
import Servicii.ServiciuEvenimentImplementation;
import Utile.TipEveniment;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EvenimentMeniu {
    private final ServiciuEveniment serviciu = ServiciuEvenimentImplementation.getInstance();
    private final Scanner sc;

    public EvenimentMeniu(Scanner sc) {
        this.sc = sc;
    }

    public void ruleaza() {
        System.out.println("\n--- OPERATII EVENIMENT ---");
        System.out.println("1. Adaugă eveniment sportiv");
        System.out.println("2. Adaugă eveniment cultural");
        System.out.println("3. Afișează eveniment după ID");
        System.out.println("4. Șterge eveniment");
        System.out.println("5. Afișează toate evenimentele");

        int opt = Integer.parseInt(sc.nextLine());
        switch (opt) {
            case 1 -> adaugaSportiv();
            case 2 -> adaugaCultural();
            case 3 -> cautaEveniment();
            case 4 -> stergeEveniment();
            case 5 -> afiseazaToate();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void adaugaSportiv() {
        System.out.print("Nume: ");
        String nume = sc.nextLine();
        System.out.print("ID locație: ");
        int idLocatie = Integer.parseInt(sc.nextLine());
        Locatie locatie = LocatieDAO.getInstance().read(idLocatie);

        LocalDateTime data = citesteData();
        System.out.print("Echipa 1: ");
        String echipa1 = sc.nextLine();
        System.out.print("Echipa 2: ");
        String echipa2 = sc.nextLine();
        System.out.print("Este derby? (true/false): ");
        boolean derby = Boolean.parseBoolean(sc.nextLine());

        serviciu.adaugaEvenimentSportiv(nume, locatie, data, echipa1, echipa2, derby);
    }


    private void adaugaCultural() {
        System.out.print("Tip (TEATRU, CONCERT, FILM): ");
        TipEveniment tip = TipEveniment.valueOf(sc.nextLine());
        System.out.print("Nume: ");
        String nume = sc.nextLine();
        System.out.print("ID locație: ");
        int idLocatie = Integer.parseInt(sc.nextLine());
        Locatie locatie = LocatieDAO.getInstance().read(idLocatie);

        LocalDateTime data = citesteData();
        System.out.print("Durata: ");
        int durata = Integer.parseInt(sc.nextLine());
        System.out.print("Limba: ");
        String limba = sc.nextLine();

        serviciu.adaugaEvenimentCultural(tip, nume, locatie, data, durata, limba);
    }


    private void cautaEveniment() {
        System.out.print("ID eveniment: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(serviciu.getEvenimentDupaId(id));
    }

    private void stergeEveniment() {
        System.out.print("ID eveniment: ");
        int id = Integer.parseInt(sc.nextLine());
        serviciu.stergeEveniment(id);
    }

    private void afiseazaToate() {
        serviciu.getToateEvenimentele().forEach(System.out::println);
    }

    private LocalDateTime citesteData() {
        System.out.print("Data (yyyy-mm-dd hh:mm): ");
        String[] parts = sc.nextLine().split("[\sT:]|");
        return LocalDateTime.of(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4])
        );
    }
}
