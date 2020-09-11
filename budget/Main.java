package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<String> entries = new ArrayList<>();
    private static double total;

    public static void main(String[] args) {
        getInput();
        processInput();
        printInput();
    }

    private static void getInput() {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            entries.add(input.nextLine());
        }
        input.close();
    }

    private static void processInput() {
        total = 0.00;
        for (var item : entries) {
            String[] splitEntry = item.split("\\$");
            total += Float.parseFloat(splitEntry[1]);
        }
    }

    private static void printInput() {
        entries.forEach(System.out::println);
        System.out.printf("\nTotal: $%.2f\n", total);
    }
}
