package budget;

public class Purchase {
    private String item;
    private double cost;

    Purchase(String item, double cost) {
        this.item = item;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public double getCost() {
        return cost;
    }
}
