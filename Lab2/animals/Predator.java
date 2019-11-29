package animals;

public class Predator extends Animal {

    public Predator(int id, String name) {
        super(id, name);
    }

    public Predator(int id, String name, Food food) {
        super(id, name, food);
    }

    public Predator(int id, String name, String foodType, int amount) {
        super(id, name, foodType, amount);
    }

    @Override
    protected Food calculateFood() {
        return new Food("MEAT", 100);
    }


}
