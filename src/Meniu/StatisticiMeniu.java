package Meniu;

import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Servicii.*;
import Servicii.ServiciuAudit;
import Utile.TipEveniment;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticiMeniu {
    private final Scanner sc;
    private final ServiciuClient serviciuClient = ServiciuClientImplementation.getInstance();
    private final ServiciuBilet serviciuBilet = ServiciuBiletImplementation.getInstance();
    private final ServiciuEveniment serviciuEveniment = ServiciuEvenimentImplementation.getInstance();
    private final ServiciuAudit audit = ServiciuAudit.getInstanta();

    public StatisticiMeniu(Scanner sc) {
        this.sc = sc;
    }

    public void ruleaza() {
        System.out.println("\n--- STATISTICI ȘI SORTĂRI ---");
        System.out.println("1. Sortare clienți după număr bilete și nume");
        System.out.println("2. Sortare bilete după preț");
        System.out.println("3. Număr de evenimente pe tip");
        System.out.print("Alege opțiunea: ");
        int opt = Integer.parseInt(sc.nextLine());
        switch (opt) {
            case 1 -> sorteazaClienti();
            case 2 -> sorteazaBilete();
            case 3 -> statisticaEvenimente();
            default -> System.out.println("Opțiune invalidă.");
        }
    }

    private void sorteazaClienti() {
        audit.scrieActiune("sortare_clienti_dupa_bilete_si_nume");
        List<Client> clienti = serviciuClient.getTotiClientii();
        clienti.sort(null); // compareTo din Client
        clienti.forEach(System.out::println);
    }

    private void sorteazaBilete() {
        audit.scrieActiune("sortare_bilete_dupa_pret");
        List<Bilet> bilete = serviciuBilet.getToateBiletele();
        bilete.sort(null); // compareTo din Bilet
        bilete.forEach(System.out::println);
    }

    private void statisticaEvenimente() {
        audit.scrieActiune("statistica_evenimente_pe_tip");
        List<Eveniment> evenimente = serviciuEveniment.getToateEvenimentele();
        Map<TipEveniment, Long> statistica = evenimente.stream()
                .collect(Collectors.groupingBy(Eveniment::getTip, Collectors.counting()));

        statistica.forEach((tip, count) -> System.out.println(tip + ": " + count));
    }
}
