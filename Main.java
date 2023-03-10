import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Machine machine = new Machine();
        boolean quit = false;

        System.out.println("Welcome to the Interactive Coffee Machine!\n");

        while (!quit) {
            printMainMenu();
            quit = getSelection(machine);
        }
    }

    private static void printMachineDetails(Machine machine) {
        System.out.println(machine.getDetails());
        System.out.println();
    }

    private static void printMainMenu() {
        System.out.println("Choose an option from the list below:" +
                "\n\tb - buy coffee" +
                "\n\tf - fill coffee machine" +
                "\n\tt - take money from coffee machine" +
                "\n\tr - show machine resources" +
                "\n\tq - quit\n");
    }

    private static boolean getSelection(Machine machine) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        switch (option) {
            // buy coffee
            case "b" -> buyCoffee(machine);

            // fill machine
            case "f" -> fillMachine(machine);

            // take money from machine
            case "t" -> takeMoneyFromMachine(machine);

            // show machine resources
            case "r" -> printMachineDetails(machine);

            // quit
            case "q" -> {
                return true;
            }

            default -> System.out.println("Invalid option!");
        }

        return false;
    }

    private static void printCoffeeMenu() {
        System.out.println("\t1 - espresso" +
                "\n\t2 - latte" +
                "\n\t3 - cappuccino");
    }

    private static void buyCoffee(Machine machine) {
        System.out.println("What would you like to buy?" +
                "\nEnter an option from the menu below:");
        printCoffeeMenu();

        int coffeeSelection = -1;

        while (coffeeSelection == -1) {
            coffeeSelection = getCoffeeSelection();
        }

        boolean coffeeMade = machine.makeCoffee(coffeeSelection);

        if (!coffeeMade) {
            System.out.println("Sorry, not enough " + machine.getInsufficientResources(coffeeSelection) + "!");
        } else {
            System.out.println("Making one " + machine.getCoffeeType(coffeeSelection));
        }
    }

    private static int getCoffeeSelection() {
        try {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            if (option != 1 && option != 2 && option != 3) {
                System.out.println("Invalid option");
                option = -1;
            }

            return option;
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
            return -1;
        }
    }

    private static void fillMachine(Machine machine) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Write how many ml of water you want to add: ");
            machine.updateWater(scanner.nextInt());

            System.out.print("Write how many ml of milk you want to add: ");
            machine.updateMilk(scanner.nextInt());

            System.out.print("Write how many grams of coffee beans you want to add: ");
            machine.updateCoffeeBeans(scanner.nextInt());

            System.out.print("Write how many ml of water you want to add: ");
            machine.updateDisposableCups(scanner.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number!");
        }
    }

    private static void takeMoneyFromMachine(Machine machine) {
        double earnings = machine.takeMoney();
        System.out.println("Your earnings are ??" + earnings);
    }
}