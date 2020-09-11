package budget;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Entries budget = new Entries();
        boolean running = true;

        while (running) {
            printMenu();
            switch (getInput()) {
                case "1":
                    budget.addIncome(addBalance());
                    System.out.println("Income was added!\n");
                    break;
                case "2":
                    budget.addPurchase(addPurchase());
                    System.out.println("Purchase was added!\n");
                    break;
                case "3":
                    budget.printPurchases();
                    break;
                case "4":
                    budget.printBalance();
                    break;
                case "0":
                    running = false;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.out.println("\nInvalid action choice. Try again.\n");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }

    private static String getInput() {
        final Scanner input = new Scanner(System.in);
        //        input.close();
        return input.nextLine();
    }

    private static double addBalance() {
        while (true) {
            System.out.println("\nEnter income:");
            String income = getInput();
            if (income.matches("\\d*\\.?\\d{0,2}")) {
                return Double.parseDouble(income);
            }
            System.out.println("Please enter valid number with no more than two decimal places.");
        }
    }

    private static Purchase addPurchase() {
        System.out.println("\nEnter purchase name:");
        String item = getInput();
        System.out.println("Enter its price:");
        String cost = getInput();
        return new Purchase(item, Double.parseDouble(cost));
    }
}
