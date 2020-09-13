package BankSystem;

import java.util.Random;
import java.util.Scanner;

public class BankMenu {

    public static void externalMenu(DatabaseManager data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create an account\n2. Log into account\n0. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                createAccount(data);
                break;
            }
            case 2: {
                logInAccount(data);
                break;
            }
            case 0: {
                System.out.println("Bye!");
                break;
            }
            default: {
                System.out.println("Incorrect");
                externalMenu(data);
            }
        }
    }


    public static void createAccount(DatabaseManager data) {
        System.out.println("Your card has been created");
        Random random = new Random();
        String number = "400000";
        String pin = String.valueOf(random.nextInt(10000 - 1000 + 1) + 1000);
        number = number.concat(String.valueOf(random.nextInt(10_0000_0000 - 10_0000_000 + 1) + 10_0000_000));
        number = number.concat(String.valueOf(ControlLuhnAlghoritm.checkValid(number)));

        data.insert(number, pin);

        System.out.println("Your card number:");
        System.out.println(number);
        System.out.println("Your card PIN:");
        System.out.println(pin);

        externalMenu(data);
    }


    public static void logInAccount(DatabaseManager data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        String number = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        if (data.getPinAccount(number).equals(pin)) {
            System.out.println("You have successfully logged in!");
            internalMenu(number, data);
        } else {
            System.out.println("Wrong card number or PIN!");
            externalMenu(data);
        }
    }


    public static void internalMenu(String number, DatabaseManager data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Balance\n2. Add Income\n3. Do transfer\n4. Close Account\n5. Log out\n0. Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("Balance: " + data.getBalanceAccount(number));
                internalMenu(number, data);
                break;
            }
            case 2: {
                System.out.println("Enter the amount");
                int deposit = scanner.nextInt();
                data.addIncome(number , deposit);
                internalMenu(number, data);
                break;
            }
            case 3: {
                CheckValidTransfer.transfer(number, data);
                internalMenu(number, data);
                break;
            }
            case 4: {
                data.closeAccount(number);
                externalMenu(data);
                break;
            }
            case 5: {
                externalMenu(data);
                break;
            }
            case 0: {
                System.out.println("Bye!");
                break;
            }
            default: {
                System.out.println("Incorrect");
                internalMenu(number, data);
            }
        }
    }


}
