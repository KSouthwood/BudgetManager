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
}
