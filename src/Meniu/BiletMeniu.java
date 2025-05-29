package Meniu;

import Entitati.Bilet;
import Servicii.ServiciuBilet;
import Servicii.ServiciuBiletImplementation;

import java.util.List;
import java.util.Scanner;


public class BiletMeniu {
    private final ServiciuBilet serviciu = ServiciuBiletImplementation.getInstance();
    private final Scanner sc;

    public BiletMeniu(Scanner sc) {
        this.sc = sc;
    }

    public void ruleaza() {
        System.out.println("\n--- OPERATII BILET ---");
        System.out.println("1. Cumpără bilet");
        System.out.println("2. Afișează bilet după ID");
        System.out.println("3. Șterge bilet");
        System.out.println("4. Afișează toate biletele");
        System.out.println("5. Afișează biletele unui client");

        int opt = Integer.parseInt(sc.nextLine());
        switch (opt) {
            case 1 -> cumpara();
            case 2 -> afiseazaDupaId();
            case 3 -> sterge();
            case 4 -> afiseazaToate();
            case 5 -> afiseazaPentruClient();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void cumpara() {
        System.out.print("ID client: ");
        int idClient = Integer.parseInt(sc.nextLine());
        System.out.print("ID eveniment: ");
        int idEveniment = Integer.parseInt(sc.nextLine());
        System.out.print("Locuri dorite (separate prin virgulă): ");
        String[] locuriStr = sc.nextLine().split(",");
        System.out.print("Preț total: ");
        double pret = Double.parseDouble(sc.nextLine());

        serviciu.cumparaBilet(idClient, idEveniment, locuriStr, pret);
    }

    private void afiseazaDupaId() {
        System.out.print("ID bilet: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println(serviciu.getBiletDupaId(id));
    }

    private void sterge() {
        System.out.print("ID bilet: ");
        int id = Integer.parseInt(sc.nextLine());
        serviciu.stergeBilet(id);
    }


    private void afiseazaToate() {
        List<Bilet> lista = serviciu.getToateBiletele();
        for (Bilet b : lista) {
            System.out.println(b);
        }
    }

    private void afiseazaPentruClient() {
        System.out.print("ID client: ");
        int id = Integer.parseInt(sc.nextLine());
        List<Bilet> lista = serviciu.getBiletePentruClient(id);
        for (Bilet b : lista) {
            System.out.println(b);
        }
    }

}
