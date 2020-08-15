package banking;

import java.util.Random;
import java.util.Scanner;

public class BankMenu {

    public static void externalMenu(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.join("\n",
                "1. Create an account", "2. Log into account", "0. Exit"));
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                createAccount();
                break;
            }
            case 2: {
                logInAccount(account);
                break;
            }
            case 0: {
                System.out.println("Bye!");
                break;
            }
            default: {
                System.out.println("Incorrect");
                externalMenu(account);
            }
        }
    }

    public static void createAccount() {
        System.out.println("Your card has been created");
        Random random = new Random();
        String number = "400000";
        String pin = String.valueOf(random.nextInt(10000 - 1000 + 1) + 1000);
        number = number.concat(String.valueOf(random.nextInt(10_0000_0000 - 10_0000_000 + 1) + 10_0000_000));
        number = number.concat(String.valueOf(checkValid(number)));

        Account ac = new Account(number, pin);
        System.out.println("Your card number:");
        System.out.println(number);
        System.out.println("Your card PIN:");
        System.out.println(pin);

        externalMenu(ac);
    }

    public static void logInAccount(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your card number:");
        String id = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String pin = scanner.nextLine();
        if (account.getId().equals(id) && account.getPin().equals(pin)) {
            System.out.println("You have successfully logged in!");
            internalMenu(account);
        } else {
            System.out.println("Wrong card number or PIN!");
            externalMenu(account);
        }
    }

    public static void internalMenu(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.join("\n",
                "1. Balance", "2. Log out", "0. Exit"));
        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                System.out.println("Balance: " + account.getBalance());
                internalMenu(account);
                break;
            }
            case 2: {
                externalMenu(account);
                break;
            }
            case 0: {
                System.out.println("Bye!");
                break;
            }
            default: {
                System.out.println("Incorrect");
                internalMenu(account);
            }
        }
    }

    static int checkValid(String pnr) {
        int extraChars = pnr.length() - 15;
        System.out.println(pnr.length());
        if (extraChars < 0) {
            throw new IllegalArgumentException("Number length must be at least 15 characters!");
        }
        pnr = pnr.substring(extraChars, 15 + extraChars);
        int sum = 0;
        for (int i = 0; i < pnr.length(); i++) {
            char tmp = pnr.charAt(i);
            int num = tmp - '0';
            int product;
            if (i % 2 != 0) {
                product = num;
            } else {
                product = num * 2;
            }
            if (product > 9)
                product -= 9;
            sum += product;
        }
        if (sum % 10 == 0) {
            return 0;
        } else {
            return (10 - (sum % 10));
        }

        // return (sum % 10 == 0);
    }
}
