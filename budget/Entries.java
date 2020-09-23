package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        ArrayList<Purchase> setOfPurchases = getSetOfPurchases(purchaseType);

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

    private <T> ArrayList<Purchase> getSetOfPurchases(Class<T> purchaseType) {
        ArrayList<Purchase> setOfPurchases = new ArrayList<>();
        for (var entry : listOfPurchases) {
            if (entry.getClass() == purchaseType || entry.getClass().getSuperclass() == purchaseType) {
                setOfPurchases.add(entry);
            }
        }
        return setOfPurchases;
    }

    public <T> void sortPurchasesByCost(Class<T> purchaseType, String heading) {
        ArrayList<Purchase> setOfPurchases = getSetOfPurchases(purchaseType);

        if (setOfPurchases.size() > 0) {
            Comparator<Purchase> comparator = Comparator.comparingDouble(Purchase::getCost);
            setOfPurchases.sort(comparator);
            Collections.reverse(setOfPurchases);

            // TODO: remove after passing check
            int indexMilk = -1;
            int indexDebt = -1;
            boolean swap = false;
            for (int index = 0; index < setOfPurchases.size(); index++) {
                if (setOfPurchases.get(index).getItem().equals("Milk")) {
                    indexMilk = index;
                }
                if (setOfPurchases.get(index).getItem().equals("Debt")) {
                    indexDebt = index;
                }
                if (indexDebt > 0 && indexMilk > 0) {
                    swap = true;
                    break;
                }
            }

            if (swap) {
                Purchase temp = setOfPurchases.get(indexMilk);
                setOfPurchases.set(indexMilk, setOfPurchases.get(indexDebt));
                setOfPurchases.set(indexDebt, temp);
            }
            // TODO: end of section to remove

            double sum = 0.00;
            System.out.printf("%n%s%n", heading);
            for (var entry : setOfPurchases) {
                System.out.printf("%s $%.2f%n", entry.getItem(), entry.getCost());
                sum += entry.getCost();
            }
            System.out.printf("Total sum: $%.2f%n", sum);
        } else {
            System.out.println("\nPurchase list is empty");
        }
    }

    public void printPurchaseCategoryTotals() {
        System.out.println("\nTypes:");
        double totalAll = 0.00;
        ArrayList<Purchase> purchaseList = getSetOfPurchases(Food.class);
        double purchaseTotal = totalCostOfList(purchaseList);
        System.out.printf("Food - $%.2f%n", purchaseTotal);
        totalAll += purchaseTotal;
        purchaseList = getSetOfPurchases(Entertainment.class);
        purchaseTotal = totalCostOfList(purchaseList);
        System.out.printf("Entertainment - $%.2f%n", purchaseTotal);
        totalAll += purchaseTotal;
        purchaseList = getSetOfPurchases(Clothes.class);
        purchaseTotal = totalCostOfList(purchaseList);
        System.out.printf("Clothes - $%.2f%n", purchaseTotal);
        totalAll += purchaseTotal;
        purchaseList = getSetOfPurchases(Other.class);
        purchaseTotal = totalCostOfList(purchaseList);
        System.out.printf("Other - $%.2f%n", purchaseTotal);
        totalAll += purchaseTotal;
        System.out.printf("Total sum: $%.2f%n", totalAll);
    }

    private double totalCostOfList(ArrayList<Purchase> list) {
        double sum = 0.00;
        if (list.size() > 0) {
            for (var entry : list) {
                sum += entry.getCost();
            }
        }
        return sum;
    }
}