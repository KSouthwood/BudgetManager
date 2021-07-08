package budget;

import java.util.Scanner;

public class Main {
    private static final Entries budget = new Entries();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            switch (getInput()) {
                case "1":
                    budget.addIncome(addBalance());
                    System.out.println("Income was added!\n");
                    break;
                case "2":
                    addPurchaseMenu();
                    break;
                case "3":
                    if (budget.getNumberOfPurchases() > 0) {
                        printPurchasesMenu();
                    } else {
                        System.out.println("\nPurchase list is empty!\n");
                    }
                    break;
                case "4":
                    budget.printBalance();
                    break;
                case "5":
                    budget.saveToFile();
                    break;
                case "6":
                    budget.loadFromFile();
                    break;
                case "7":
                    analyzeMenu();
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

    private static void printMainMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
    }

    private static String getInput() {
        final Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Gets an amount from the user. Verifies it's a valid positive decimal number.
     * @return amount to add
     */
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

    /**
     * Get a purchase and its price from the user.
     * @return string array containing the purchase info
     */
    private static String[] addPurchase() {
        String[] purchaseEntry = new String[2];
        System.out.println("\nEnter purchase name:");
        purchaseEntry[0] = getInput();
        System.out.println("Enter its price:");
        purchaseEntry[1] = getInput();
        System.out.println("Purchase was added!");
        return purchaseEntry;
    }

    private static void addPurchaseMenu() {
        String[] purchaseEntry;

        while (true) {
            printMenuAddPurchase();
            switch (getInput()) {
                case "1":
                    purchaseEntry = addPurchase();
                    budget.addPurchase(new Food(purchaseEntry[0], Double.parseDouble(purchaseEntry[1])));
                    break;
                case "2":
                    purchaseEntry = addPurchase();
                    budget.addPurchase(new Clothes(purchaseEntry[0], Double.parseDouble(purchaseEntry[1])));
                    break;
                case "3":
                    purchaseEntry = addPurchase();
                    budget.addPurchase(new Entertainment(purchaseEntry[0], Double.parseDouble(purchaseEntry[1])));
                    break;
                case "4":
                    purchaseEntry = addPurchase();
                    budget.addPurchase(new Other(purchaseEntry[0], Double.parseDouble(purchaseEntry[1])));
                    break;
                case "5":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void printMenuAddPurchase() {
        System.out.println("\nChoose the type of purchase:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
    }

    private static void printPurchasesMenu() {
        while (true) {
            printMenuPrintPurchases();
            switch (getInput()) {
                case "1":
                    System.out.println("\nFood:");
                    budget.printPurchases(Food.class);
                    break;
                case "2":
                    System.out.println("\nClothes:");
                    budget.printPurchases(Clothes.class);
                    break;
                case "3":
                    System.out.println("\nEntertainment:");
                    budget.printPurchases(Entertainment.class);
                    break;
                case "4":
                    System.out.println("\nOther:");
                    budget.printPurchases(Other.class);
                    break;
                case "5":
                    System.out.println("\nAll:");
                    budget.printPurchases(Purchase.class);
                    break;
                case "6":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

    }

    private static void printMenuPrintPurchases() {
        System.out.println("\nChoose the type of purchase:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
    }

    private static void printMenuAnalyze() {
        System.out.println("\nHow do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");
    }

    private static void analyzeMenu() {
        while (true) {
            printMenuAnalyze();
            switch (getInput()) {
                case "1":
                    budget.sortPurchasesByCost(Purchase.class, "All:");
                    break;
                case "2":
                    budget.printPurchaseCategoryTotals();
                    break;
                case "3":
                    sortByCategory();
                    break;
                case "4":
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void printSortCategoryMenu() {
        System.out.println("\nChoose the type of purchase:");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
    }

    private static void sortByCategory() {
        while (true) {
            printSortCategoryMenu();
            switch (getInput()) {
                case "1":
                    budget.sortPurchasesByCost(Food.class, "Food:");
                    return;
                case "2":
                    budget.sortPurchasesByCost(Clothes.class, "Clothes:");
                    return;
                case "3":
                    budget.sortPurchasesByCost(Entertainment.class, "Entertainment:");
                    return;
                case "4":
                    budget.sortPurchasesByCost(Other.class, "Other:");
                    return;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
