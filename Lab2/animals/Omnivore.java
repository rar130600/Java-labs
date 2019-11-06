package animals;

public class Omnivore extends Animal{

    public Omnivore(int id, String name) {
        super(id, name);
    }

    public Omnivore(int id, String name, Food food) {
        super(id, name, food);
    }

    public Omnivore(int id, String name, String foodType, int amount) {
        super(id, name, foodType, amount);
    }

    @Override
    protected Food calculateFood() {
        return new Food("ANY", 70);
    }
}
