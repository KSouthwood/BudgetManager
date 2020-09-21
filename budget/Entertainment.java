package budget;

public class Entertainment extends Purchase {
    public Entertainment(String item, double cost) {
        super(item, cost);
        this.category = "Entertainment";
    }
}
