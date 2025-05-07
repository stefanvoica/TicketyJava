import Entitati.Client;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Client client1 = new Client("Popescu Ion", "ion@gmail.com", 15, "Bucuresti");
        Client client2 = new Client("Ionescu George", "george@gmail.com", 25, "Ploiesti");
        Client client3 = new Client("Ion Marcel", "marcel@yahoo.com", 30, "Constanta");
        System.out.println(client1.toString());
    }
}