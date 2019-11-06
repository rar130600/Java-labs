package animals;

import java.io.Serializable;

public abstract class Animal implements Serializable {
    private int id;
    private String name;
    private Food food;

    protected Animal(int id, String name) {
        this(id, name, null);
    }

    protected Animal(int id, String name, Food food) {
        this.id = id;
        this.name = name;
        this.food = food;
    }

    protected Animal(int id, String name, String foodType, int amount) {
        if (amount < 0)
        {
            throw new IllegalArgumentException("Amound of food must be > 0");
        }
        this.id = id;
        this.name = name;
        this.food = new Food(foodType, amount);
    }

    abstract protected Food calculateFood();

    @Override
    public  String toString() {
        return "ID = " + getId() +
                ", Name = " + getName() +
                ", Food type: " + getFood().getFoodType() +
                ", Amount of food: " + getFood().getAmount();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Food getFood() {
        return (food == null) ? food = calculateFood() : food;
    }
}
