package BankSystem;

import java.util.Scanner;

public class CheckValidTransfer {

    public static void transfer(String fromNumber, DatabaseManager data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where do you want to transfer");
        String toNumber = scanner.nextLine();
        if (!toNumber.equals(fromNumber)) {
            int luhn = ControlLuhnAlghoritm.checkValid(toNumber.substring(0, 15));

            if (Integer.parseInt(String.valueOf(toNumber.charAt(15))) == luhn) {
                if(data.checkExist(toNumber)) {

                    System.out.println("How much do you want to transfer");
                    int transfer = scanner.nextInt();

                    if (data.getBalanceAccount(fromNumber) > transfer) {
                        data.doTransfer(fromNumber, toNumber, transfer);
                        System.out.println("\nSuccessfully\n");

                    } else {
                        System.out.println("Not enough money!");
                        BankMenu.internalMenu(fromNumber, data);
                    }
                } else {
                    System.out.println("Such a card does not exist.");
                }

            } else {
                System.out.println("Probably you made mistake in the card number. Please try again!");
                BankMenu.internalMenu(fromNumber, data);
            }

        } else {
            System.out.println("You can't transfer money to the same account!");
            BankMenu.internalMenu(fromNumber, data);
        }

    }
}
