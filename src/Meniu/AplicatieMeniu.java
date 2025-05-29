package Meniu;

import Meniu.*;

import java.util.Scanner;

public class AplicatieMeniu {
    private final Scanner sc = new Scanner(System.in);

    public void ruleaza() {
        LocatieMeniu locatieMeniu = new LocatieMeniu(sc);
        ClientMeniu clientMeniu = new ClientMeniu(sc);
        EvenimentMeniu evenimentMeniu = new EvenimentMeniu(sc);
        BiletMeniu biletMeniu = new BiletMeniu(sc);
        StatisticiMeniu statisticiMeniu = new StatisticiMeniu(sc);

        int optiune;
        do {
            System.out.println("\n=== MENIU PRINCIPAL ===");
            System.out.println("1. Operatii Locatie");
            System.out.println("2. Operatii Client");
            System.out.println("3. Operatii Eveniment");
            System.out.println("4. Operatii Bilet");
            System.out.println("5. Statistici și sortări");
            System.out.println("0. Ieșire");
            System.out.print("Alege o categorie: ");
            optiune = Integer.parseInt(sc.nextLine());
            switch (optiune) {
                case 1 -> locatieMeniu.ruleaza();
                case 2 -> clientMeniu.ruleaza();
                case 3 -> evenimentMeniu.ruleaza();
                case 4 -> biletMeniu.ruleaza();
                case 5 -> statisticiMeniu.ruleaza();
                case 0 -> System.out.println("La revedere!");
                default -> System.out.println("Opțiune invalidă!");
            }
        } while (optiune != 0);
    }
}
