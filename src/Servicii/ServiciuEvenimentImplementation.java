package Servicii;

import DAO.EvenimentDAO;
import DAO.LocatieDAO;
import Entitati.Eveniment;
import Entitati.EvenimentCultural;
import Entitati.EvenimentSportiv;
import Entitati.Locatie;
import Utile.TipEveniment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ServiciuEvenimentImplementation implements ServiciuEveniment {
    private static ServiciuEvenimentImplementation instance = null;
    private final EvenimentDAO evenimentDAO = EvenimentDAO.getInstance();
    private final LocatieDAO locatieDAO = LocatieDAO.getInstance();
    private final ServiciuAudit audit = ServiciuAudit.getInstanta();

    private ServiciuEvenimentImplementation() {}

    public static ServiciuEvenimentImplementation getInstance() {
        if (instance == null) instance = new ServiciuEvenimentImplementation();
        return instance;
    }

    @Override
    public Eveniment adaugaEveniment(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data) {
        Eveniment eveniment = new Eveniment(tip, nume, locatie, data);
        evenimentDAO.create(eveniment);
        audit.scrieActiune("adauga_eveniment");
        System.out.println("Eveniment adăugat: " + eveniment);
        return eveniment;
    }

    @Override
    public Eveniment adaugaEvenimentSportiv(String nume, Locatie locatie, LocalDateTime data,
                                            String echipa1, String echipa2, boolean esteDerby) {
        EvenimentSportiv es = new EvenimentSportiv(nume, locatie, data, echipa1, echipa2, esteDerby);
        evenimentDAO.create(es);
        audit.scrieActiune("adauga_eveniment_sportiv");
        System.out.println("Eveniment sportiv adăugat: " + es);
        return es;
    }

    @Override
    public Eveniment adaugaEvenimentCultural(TipEveniment tip, String nume, Locatie locatie, LocalDateTime data,
                                             int durata, String limba) {
        EvenimentCultural ec = new EvenimentCultural(tip, nume, locatie, data, durata, limba);
        evenimentDAO.create(ec);
        audit.scrieActiune("adauga_eveniment_cultural");
        System.out.println("Eveniment cultural adăugat: " + ec);
        return ec;
    }

    @Override
    public void stergeEveniment(int idEveniment) {
        evenimentDAO.delete(idEveniment);
        audit.scrieActiune("sterge_eveniment");
        System.out.println("Eveniment șters cu ID-ul: " + idEveniment);
    }

    public void actualizeazaEveniment(int id, String numeNou, Locatie locatieNoua, LocalDateTime dataNoua) {
        Eveniment ev = new Eveniment(id, null, numeNou, locatieNoua, dataNoua);
        evenimentDAO.update(ev);
        audit.scrieActiune("actualizeaza_eveniment");
        System.out.println("Eveniment actualizat.");
    }

    @Override
    public List<Eveniment> getEvenimenteDupaTip(TipEveniment tip) {
        audit.scrieActiune("citeste_evenimente_dupa_tip");
        return evenimentDAO.getEvenimenteDupaTip(tip);
    }

    @Override
    public List<Eveniment> getEvenimenteViitoare() {
        audit.scrieActiune("citeste_evenimente_viitoare");
        return evenimentDAO.getEvenimenteViitoare();
    }

    @Override
    public Eveniment getEvenimentDupaId(int id) {
        audit.scrieActiune("citeste_eveniment_dupa_id");
        return evenimentDAO.read(id);
    }

    @Override
    public void rezervaLocuri(Eveniment eveniment, Set<Integer> locuri) {
        Set<Integer> libere = eveniment.getLocuriLibere();
        if (!libere.containsAll(locuri)) {
            throw new IllegalArgumentException("Locuri indisponibile.");
        }
        eveniment.rezervaLocuri(locuri);
        audit.scrieActiune("rezerva_locuri_eveniment");
        System.out.println("Rezervare efectuată: " + locuri);
    }

    @Override
    public void elibereazaLocuri(Eveniment eveniment, Set<Integer> locuri) {
        eveniment.getLocuriOcupate().removeAll(locuri);
        audit.scrieActiune("elibereaza_locuri_eveniment");
        System.out.println("Locuri eliberate: " + locuri);
    }

    @Override
    public List<Eveniment> getToateEvenimentele() {
        audit.scrieActiune("citeste_toate_evenimentele");
        return evenimentDAO.readAll();
    }
}
