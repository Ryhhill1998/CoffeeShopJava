public class Machine {

    private int waterAvailable;
    private int milkAvailable;
    private int coffeeBeansAvailable;
    private int disposableCupsAvailable;
    private int money;
    private static final int ESPRESSO_OPTION = 1;
    private static final int LATTE_OPTION = 2;
    private static final int CAPPUCCINO_OPTION = 3;

    public Machine() {
        this.waterAvailable = 400;
        this.milkAvailable = 540;
        this.coffeeBeansAvailable = 120;
        this.disposableCupsAvailable = 9;
        this.money = 550;
    }

    public String getDetails() {
        return "The coffee machine has:" +
                "\n" + waterAvailable + " ml of water" +
                "\n" + milkAvailable + " ml of milk" +
                "\n" + coffeeBeansAvailable + " g of coffee beans" +
                "\n" + disposableCupsAvailable + " disposable cups" +
                "\n£" + money + " of money";
    }

    private boolean canMakeCoffee(Coffee coffee) {
        return coffee.getWaterRequired() <= waterAvailable && coffee.getMilkRequired() <= milkAvailable
                && coffee.getCoffeeBeansRequired() <= coffeeBeansAvailable
                && coffee.getCupsRequired() <= disposableCupsAvailable;
    }

    private void updateStock(Coffee coffee) {
        updateWater(-coffee.getWaterRequired());
        updateMilk(-coffee.getMilkRequired());
        updateCoffeeBeans(-coffee.getCoffeeBeansRequired());
        updateDisposableCups(-coffee.getCupsRequired());
        updateMoney(coffee.getPrice());
    }

    public boolean makeCoffee(int option) {
        Coffee coffee = getCoffeeOfType(option);

        if (canMakeCoffee(coffee)) {
            updateStock(coffee);
            return true;
        }

        return false;
    }

    public String getCoffeeType(int option) {
        if (option == ESPRESSO_OPTION) {
            return "espresso";
        }

        if (option == CAPPUCCINO_OPTION) {
            return "cappuccino";
        }

        return "latte";
    }

    private void addCommaIfNeeded(StringBuilder stringBuilder) {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append(", ");
        }
    }

    private Coffee getCoffeeOfType(int option) {
        if (option == ESPRESSO_OPTION) {
            return new Espresso();
        }

        if (option == CAPPUCCINO_OPTION) {
            return new Cappuccino();
        }

        return new Latte();
    }

    public String getInsufficientResources(int option) {
        Coffee coffee = getCoffeeOfType(option);

        StringBuilder stringBuilder = new StringBuilder();
        if (coffee.getWaterRequired() > waterAvailable) {
            stringBuilder.append("water");
        }

        if (coffee.getMilkRequired() > milkAvailable) {
            addCommaIfNeeded(stringBuilder);
            stringBuilder.append("milk");
        }

        if (coffee.getCoffeeBeansRequired() > coffeeBeansAvailable) {
            addCommaIfNeeded(stringBuilder);
            stringBuilder.append("coffee beans");
        }

        if (coffee.getCupsRequired() > disposableCupsAvailable) {
            addCommaIfNeeded(stringBuilder);
            stringBuilder.append("disposable cups");
        }

        return stringBuilder.toString();
    }

    public void updateWater(int amount) {
        waterAvailable += amount;
    }

    public void updateMilk(int amount) {
        milkAvailable += amount;
    }

    public void updateCoffeeBeans(int amount) {
        coffeeBeansAvailable += amount;
    }

    public void updateDisposableCups(int amount) {
        disposableCupsAvailable += amount;
    }

    private void updateMoney(int amount) {
        money += amount;
    }

    public int takeMoney() {
        int earnings = money;
        money = 0;
        return earnings;
    }
}
