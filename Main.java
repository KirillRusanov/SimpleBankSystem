package BankSystem;

public class Main {

    public static void main(String[] args) {
        if (args[0].equals("-fileName")) {
            DatabaseManager data = new DatabaseManager(args[1]);
            BankMenu.externalMenu(data);


        }
    }
}
