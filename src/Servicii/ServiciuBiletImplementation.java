package Servicii;

import DAO.BiletDAO;
import DAO.ClientDAO;
import DAO.EvenimentDAO;
import Entitati.Bilet;
import Entitati.Client;
import Entitati.Eveniment;
import Utile.Status;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiciuBiletImplementation implements ServiciuBilet {
    private static ServiciuBiletImplementation instance = null;
    private final BiletDAO biletDAO = BiletDAO.getInstance();
    private final ClientDAO clientDAO = ClientDAO.getInstance();
    private final EvenimentDAO evenimentDAO = EvenimentDAO.getInstance();
    private final ServiciuAudit audit = ServiciuAudit.getInstanta();

    private ServiciuBiletImplementation() {}

    public static ServiciuBiletImplementation getInstance() {
        if (instance == null) instance = new ServiciuBiletImplementation();
        return instance;
    }

    public void cumparaBilet(int idClient, int idEveniment, String[] locuriStr, double pret) {
        Client client = clientDAO.read(idClient);
        Eveniment eveniment = evenimentDAO.read(idEveniment);

        Set<Integer> locuriDorite = new HashSet<>();
        for (String l : locuriStr) {
            locuriDorite.add(Integer.parseInt(l.trim()));
        }

        Set<Integer> locuriDisponibile = eveniment.getLocuriLibere();
        if (!locuriDisponibile.containsAll(locuriDorite)) {
            throw new IllegalArgumentException("Unele locuri dorite nu sunt disponibile.");
        }

        eveniment.rezervaLocuri(locuriDorite);
        Bilet bilet = new Bilet(eveniment, client, locuriDorite, pret);
        biletDAO.create(bilet);

        audit.scrieActiune("cumpara_bilet");
        System.out.println("Bilet cumpărat cu succes: " + bilet);
    }

    public void stergeBilet(int idBilet) {
        biletDAO.delete(idBilet);
        audit.scrieActiune("sterge_bilet");
        System.out.println("Bilet șters.");
    }

    public Bilet getBiletDupaId(int id) {
        audit.scrieActiune("citeste_bilet_dupa_id");
        return biletDAO.read(id);
    }

    public List<Bilet> getToateBiletele() {
        audit.scrieActiune("citeste_toate_biletele");
        return biletDAO.readAll();
    }

    public List<Bilet> getBiletePentruClient(int idClient) {
        audit.scrieActiune("citeste_bilete_client");
        return biletDAO.readByClientId(idClient);
    }

    public boolean valideazaBilet(int idBilet) {
        Bilet bilet = biletDAO.read(idBilet);
        if (bilet.getValidat() == Status.VALIDAT) {
            audit.scrieActiune("valideaza_bilet_deja_valid");
            return false;
        }
        bilet.setValidat(Status.VALIDAT);
        biletDAO.update(bilet);
        audit.scrieActiune("valideaza_bilet");
        return true;
    }
}
