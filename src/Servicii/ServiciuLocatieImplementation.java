package Servicii;

import DAO.LocatieDAO;
import Entitati.Locatie;

import java.util.List;

public class ServiciuLocatieImplementation implements ServiciuLocatie {
    private static ServiciuLocatieImplementation instance = null;
    private final LocatieDAO locatieDAO = LocatieDAO.getInstance();
    private final ServiciuAudit audit = ServiciuAudit.getInstanta();

    private ServiciuLocatieImplementation() {}

    public static ServiciuLocatieImplementation getInstance() {
        if (instance == null) instance = new ServiciuLocatieImplementation();
        return instance;
    }

    @Override
    public void adaugaLocatie(String nume, String adresa, int capacitate) {
        Locatie locatie = new Locatie(nume, adresa, capacitate);
        locatieDAO.create(locatie);
        audit.scrieActiune("adauga_locatie");
        System.out.println("Locația a fost adăugată cu ID: " + locatie.getId());
    }

    @Override
    public void stergeLocatie(int id) {
        locatieDAO.delete(id);
        audit.scrieActiune("sterge_locatie");
        System.out.println("Locația a fost ștearsă cu ID-ul: " + id);
    }

    @Override
    public Locatie getLocatieDupaId(int id) {
        audit.scrieActiune("citeste_locatie_dupa_id");
        return locatieDAO.read(id);
    }

    @Override
    public void actualizeazaLocatie(int id, String nume, String adresa, int capacitate) {
        Locatie locatie = new Locatie(id, nume, adresa, capacitate);
        locatieDAO.update(locatie);
        audit.scrieActiune("actualizeaza_locatie");
        System.out.println("Locația a fost actualizată.");
    }

    @Override
    public List<Locatie> getToateLocatiile() {
        audit.scrieActiune("citeste_toate_locatiile");
        return locatieDAO.readAll();
    }
}
