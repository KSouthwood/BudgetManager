package budget;

public class Other extends Purchase {
    public Other(String item, double cost) {
        super(item, cost);
        this.category = "Other";
    }
}
