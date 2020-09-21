package budget;

import java.io.*;
import java.util.ArrayList;

public class Entries {
    private ArrayList<Purchase> listOfPurchases;
    private double balance;
    private double purchaseTotal;

    public Entries() {
        this.listOfPurchases = new ArrayList<>();
        this.balance = 0;
        this.purchaseTotal = 0;
    }

    public void printBalance() {
        System.out.printf("%nBalance: $%.2f%n%n", balance);
    }

    public <T> void printPurchases(Class<T> purchaseType) {
        ArrayList<Purchase> setOfPurchases = new ArrayList<>();
        for (var entry : listOfPurchases) {
            if (entry.getClass() == purchaseType || entry.getClass().getSuperclass() == purchaseType) {
                setOfPurchases.add(entry);
            }
        }

        if (setOfPurchases.size() > 0) {
            double sum = 0;
            for (var entry : setOfPurchases) {
                System.out.printf("%s $%.2f%n", entry.getItem(), entry.getCost());
                sum += entry.getCost();
            }
            System.out.printf("Total sum: $%.2f%n", sum);
        } else {
            System.out.println("Purchase list is empty");
        }
    }

    public void addIncome(double income) {
        this.balance += income;
    }

    public void addPurchase(Purchase purchase) {
        balance -= purchase.getCost();
        purchaseTotal += purchase.getCost();
        listOfPurchases.add(purchase);
    }

    public int getNumberOfPurchases() {
        return listOfPurchases.size();
    }

    public void saveToFile() {
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter("purchases.txt"))) {
            for (var entry : listOfPurchases) {
                outputFile.write(entry.toString());
            }
            outputFile.write(String.format("Balance:%.2f%n", balance));
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void loadFromFile() {
        listOfPurchases.clear(); // start from a clean slate
        purchaseTotal = 0.00;
        String inputLine;
        try (BufferedReader inputFile = new BufferedReader(new FileReader("purchases.txt"))) {
            while ((inputLine = inputFile.readLine()) != null) {
                String[] input = inputLine.split(":");
                switch (input[0]) {
                    case "Balance":
                        this.balance = Double.parseDouble(input[1]);
                        break;
                    case "Food":
                        addPurchase(new Food(input[1], Double.parseDouble(input[2])));
                        break;
                    case "Clothes":
                        addPurchase(new Clothes(input[1], Double.parseDouble(input[2])));
                        break;
                    case "Entertainment":
                        addPurchase(new Entertainment(input[1], Double.parseDouble(input[2])));
                        break;
                    case "Other":
                        addPurchase(new Other(input[1], Double.parseDouble(input[2])));
                        break;
                    default:
                        System.out.println("Unknown entry: " + inputLine);
                        break;
                }
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
