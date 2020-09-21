package budget;

public class Purchase {
    private String item;
    private double cost;
    protected String category;

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

    @Override
    public String toString() {
        return category + ':' + item + ':' + cost + '\n';
    }
}
