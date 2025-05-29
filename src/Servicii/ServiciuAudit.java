package Servicii;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiciuAudit {
    private static ServiciuAudit instanta = null;
    private static final String NUME_FISIER = "audit.csv";
    private static final DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private ServiciuAudit() {}

    public static ServiciuAudit getInstanta() {
        if (instanta == null) {
            instanta = new ServiciuAudit();
        }
        return instanta;
    }

    public void scrieActiune(String numeActiune) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NUME_FISIER, true))) {
            String timestamp = LocalDateTime.now().format(FORMAT_DATA);
            writer.println(numeActiune + "," + timestamp);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în audit.csv: " + e.getMessage());
        }
    }
}
