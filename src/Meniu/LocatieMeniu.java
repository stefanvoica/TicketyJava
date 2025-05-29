package Meniu;

import Exceptii.NumeExceptie;
import Servicii.ServiciuLocatie;
import Servicii.ServiciuLocatieImplementation;

import java.util.Scanner;

public class LocatieMeniu {
    private final ServiciuLocatie serviciu = ServiciuLocatieImplementation.getInstance();
    private final Scanner sc;

    public LocatieMeniu(Scanner sc) {
        this.sc = sc;
    }

    public void ruleaza() {
        System.out.println("\n--- OPERATII LOCATIE ---");
        System.out.println("1. Adaugă locație");
        System.out.println("2. Caută locație după ID");
        System.out.println("3. Actualizează locație");
        System.out.println("4. Șterge locație");
        System.out.println("5. Afișează toate locațiile");

        int opt = Integer.parseInt(sc.nextLine());
        switch (opt) {
            case 1 -> adaugaLocatie();
            case 2 -> cautaLocatieDupaId();
            case 3 -> actualizeazaLocatie();
            case 4 -> stergeLocatie();
            case 5 -> afiseazaToate();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void adaugaLocatie() {
        try {
            System.out.print("Nume: ");
            String nume = sc.nextLine();
            if (nume.equals(""))
                throw new NumeExceptie();
            System.out.print("Adresă: ");
            String adresa = sc.nextLine();
            System.out.print("Capacitate: ");
            int capacitate = Integer.parseInt(sc.nextLine());
            serviciu.adaugaLocatie(nume, adresa, capacitate);
        }
        catch(NumeExceptie e)
        {
            System.out.println(e.getMessage());
        }


    }

    private void cautaLocatieDupaId() {
        System.out.print("ID locație: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(serviciu.getLocatieDupaId(id));
    }

    private void actualizeazaLocatie() {
        System.out.print("ID locație: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nume nou: ");
        String nume = sc.nextLine();
        System.out.print("Adresă nouă: ");
        String adresa = sc.nextLine();
        System.out.print("Capacitate nouă: ");
        int capacitate = Integer.parseInt(sc.nextLine());
        serviciu.actualizeazaLocatie(id, nume, adresa, capacitate);
    }

    private void stergeLocatie() {
        System.out.print("ID locație: ");
        int id = Integer.parseInt(sc.nextLine());
        serviciu.stergeLocatie(id);
    }

    private void afiseazaToate() {
        serviciu.getToateLocatiile().forEach(System.out::println);
    }
}
