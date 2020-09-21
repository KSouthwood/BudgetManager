package budget;

public class Food extends Purchase {
    Food(String item, double cost) {
        super(item, cost);
        this.category = "Food";
    }
}
