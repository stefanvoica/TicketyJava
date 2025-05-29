package Meniu;

import Servicii.ServiciuClient;
import Servicii.ServiciuClientImplementation;

import java.util.Scanner;

public class ClientMeniu {
    private final ServiciuClient serviciu = ServiciuClientImplementation.getInstance();
    private final Scanner sc;

    public ClientMeniu(Scanner sc) {
        this.sc = sc;
    }

    public void ruleaza() {
        System.out.println("\n--- OPERATII CLIENT ---");
        System.out.println("1. Adaugă client");
        System.out.println("2. Caută client după ID");
        System.out.println("3. Actualizează client");
        System.out.println("4. Șterge client");
        System.out.println("5. Afișează toți clienții");

        int opt = Integer.parseInt(sc.nextLine());
        switch (opt) {
            case 1 -> adaugaClient();
            case 2 -> cautaClientDupaId();
            case 3 -> actualizeazaClient();
            case 4 -> stergeClient();
            case 5 -> afiseazaToti();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void adaugaClient() {
        System.out.print("Nume: ");
        String nume = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Vârstă: ");
        int varsta = Integer.parseInt(sc.nextLine());
        System.out.print("Adresă: ");
        String adresa = sc.nextLine();
        serviciu.adaugaClient(nume, email, varsta, adresa);
    }

    private void cautaClientDupaId() {
        System.out.print("ID client: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(serviciu.getClientDupaId(id));
    }

    private void actualizeazaClient() {
        System.out.print("ID client: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nume nou: ");
        String nume = sc.nextLine();
        System.out.print("Email nou: ");
        String email = sc.nextLine();
        System.out.print("Vârstă nouă: ");
        int varsta = Integer.parseInt(sc.nextLine());
        System.out.print("Adresă nouă: ");
        String adresa = sc.nextLine();
        serviciu.actualizeazaClient(id, nume, email, varsta, adresa);
    }

    private void stergeClient() {
        System.out.print("ID client: ");
        int id = Integer.parseInt(sc.nextLine());
        serviciu.stergeClient(id);
    }

    private void afiseazaToti() {
        serviciu.getTotiClientii().forEach(System.out::println);
    }
}