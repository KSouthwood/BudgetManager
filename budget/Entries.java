package budget;

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

    public void printPurchases() {
        System.out.println();
        if (listOfPurchases.size() > 0) {
            for (var entry : listOfPurchases) {
                System.out.printf("%s $%.2f%n", entry.getItem(), entry.getCost());
            }
            System.out.printf("Total sum: $%.2f%n", purchaseTotal);
        } else {
            System.out.println("Purchase list is empty");
        }
        System.out.println();
    }

    public void addIncome(double income) {
        this.balance += income;
    }

    public void addPurchase(Purchase purchase) {
        balance -= purchase.getCost();
        purchaseTotal += purchase.getCost();
        listOfPurchases.add(purchase);
    }
}
