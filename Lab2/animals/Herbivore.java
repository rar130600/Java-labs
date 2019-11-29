package animals;

public class Herbivore extends Animal{

    public Herbivore(int id, String name) {
        super(id, name);
    }

    public Herbivore(int id, String name, Food food) {
        super(id, name, food);
    }

    public Herbivore(int id, String name, String foodType, int amount) {
        super(id, name, foodType, amount);
    }

    @Override
    protected Food calculateFood() {
        return new Food("HERBS", 50);
    }
}
