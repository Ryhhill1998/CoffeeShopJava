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

    private boolean makeEspresso() {
        Espresso espresso = new Espresso();

        if (canMakeCoffee(espresso)) {
            updateStock(espresso);
            return true;
        }

        return false;
    }

    private boolean makeCappuccino() {
        Cappuccino cappuccino = new Cappuccino();

        if (canMakeCoffee(cappuccino)) {
            updateStock(cappuccino);
            return true;
        }

        return false;
    }

    private boolean makeLatte() {
        Latte latte = new Latte();

        if (canMakeCoffee(latte)) {
            updateStock(latte);
            return true;
        }

        return false;
    }

    public boolean sellCoffee(int option) {
        if (option == ESPRESSO_OPTION) {
            return makeEspresso();
        }

        if (option == LATTE_OPTION) {
            return makeLatte();
        }

        return makeCappuccino();
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