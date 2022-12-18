public abstract class Coffee {

    private String name;
    private int waterRequired;
    private int milkRequired;
    private int coffeeBeansRequired;
    private int cupsRequired;
    private int price;

    public Coffee(String name, int waterRequired, int milkRequired, int coffeeBeansRequired, int price) {
        this.name = name;
        this.waterRequired = waterRequired;
        this.milkRequired = milkRequired;
        this.coffeeBeansRequired = coffeeBeansRequired;
        cupsRequired = 1;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getWaterRequired() {
        return waterRequired;
    }

    public int getMilkRequired() {
        return milkRequired;
    }

    public int getCoffeeBeansRequired() {
        return coffeeBeansRequired;
    }

    public int getCupsRequired() {
        return cupsRequired;
    }

    public int getPrice() {
        return price;
    }
}