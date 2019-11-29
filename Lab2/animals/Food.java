package animals;

import java.io.Serializable;

public class Food implements Serializable {
    private String foodType;
    private int amount;

    public Food(String foodType, int amount) {
        this.foodType = foodType;
        this.amount = amount;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getAmount() {
        return amount;
    }
}
