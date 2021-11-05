package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static final int ESPRESSO_ML_WATER_ON_ONE_CUP = 250;
    static final int ESPRESSO_G_COFFEE_BEANS_ON_ONE_CUP = 16;
    static final int ESPRESSO_MONEY = 4;

    static final int LATTE_ML_WATER_ON_ONE_CUP = 350;
    static final int LATTE_ML_MILK_ON_ONE_CUP = 75;
    static final int LATTE_G_COFFEE_BEANS_ON_ONE_CUP = 20;
    static final int LATTE_MONEY = 7;

    static final int CAPPUCCINO_ML_WATER_ON_ONE_CUP = 200;
    static final int CAPPUCCINO_ML_MILK_ON_ONE_CUP = 100;
    static final int CAPPUCCINO_G_COFFEE_BEANS_ON_ONE_CUP = 12;
    static final int CAPPUCCINO_MONEY = 6;

    static int water = 400;
    static int milk = 540;
    static int coffeeBeans = 120;
    static int cups = 9;
    static int money = 550;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String action;
        do {
            action = scanner.nextLine();
            mode(action);
        } while (!"exit".equals(action));
    }

    private static void remaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", coffeeBeans);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("%d of money\n\n", money);
    }

    private static void mode(String action) {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        switch (action) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                break;
        }
    }

    private static void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String type = scanner.nextLine();
        switch (type) {
            case "1":
                if (enoughResources(type)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    money += ESPRESSO_MONEY;
                    createEspresso();
                } else {
                    System.out.printf("Sorry, not enough %s!\n\n", whatIsMissing(type));
                }
                break;
            case "2":
                if (enoughResources(type)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    money += LATTE_MONEY;
                    createLatte();
                } else {
                    System.out.printf("Sorry, not enough %s!\n\n", whatIsMissing(type));
                }
                break;
            case "3":
                if (enoughResources(type)) {
                    System.out.println("I have enough resources, making you a coffee!\n");
                    money += CAPPUCCINO_MONEY;
                    createCappuccino();
                } else {
                    System.out.printf("Sorry, not enough %s!\n\n", whatIsMissing(type));
                }
                break;
            case "back":
                break;
            default:
                buy();
        }
    }

    private static boolean enoughResources(String type) {
        switch (type) {
            case "1":
                return water - ESPRESSO_ML_WATER_ON_ONE_CUP >= 0
                        && coffeeBeans - ESPRESSO_G_COFFEE_BEANS_ON_ONE_CUP >= 0
                        && cups - 1 >= 0;
            case "2":
                return water - LATTE_ML_WATER_ON_ONE_CUP >= 0
                        && milk - LATTE_ML_MILK_ON_ONE_CUP >= 0
                        && coffeeBeans - LATTE_G_COFFEE_BEANS_ON_ONE_CUP >= 0
                        && cups - 1 >= 0;
            case "3":
                return water - CAPPUCCINO_ML_WATER_ON_ONE_CUP >= 0
                        && milk - CAPPUCCINO_ML_MILK_ON_ONE_CUP >= 0
                        && coffeeBeans - CAPPUCCINO_G_COFFEE_BEANS_ON_ONE_CUP >= 0
                        && cups - 1 >= 0;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private static String whatIsMissing(String type) {
        switch (type) {
            case "1":
                if (water - ESPRESSO_ML_WATER_ON_ONE_CUP < 0) {
                    return "water";
                } else if (coffeeBeans - ESPRESSO_G_COFFEE_BEANS_ON_ONE_CUP < 0) {
                    return "coffeeBeans";
                } else {
                    return "cups";
                }
            case "2":
                if (water - LATTE_ML_WATER_ON_ONE_CUP < 0) {
                    return "water";
                } else if (milk - LATTE_ML_MILK_ON_ONE_CUP < 0) {
                    return "milk";
                } else if (coffeeBeans - LATTE_G_COFFEE_BEANS_ON_ONE_CUP < 0) {
                    return "coffeeBeans";
                } else {
                    return "cups";
                }
            case "3":
                if (water - CAPPUCCINO_ML_WATER_ON_ONE_CUP < 0) {
                    return "water";
                } else if (milk - CAPPUCCINO_ML_MILK_ON_ONE_CUP < 0) {
                    return "milk";
                } else if (coffeeBeans - CAPPUCCINO_G_COFFEE_BEANS_ON_ONE_CUP < 0) {
                    return "coffeeBeans";
                } else {
                    return "cups";
                }
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private static void createEspresso() {
        water -= ESPRESSO_ML_WATER_ON_ONE_CUP;
        coffeeBeans -= ESPRESSO_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private static void createLatte() {
        water -= LATTE_ML_WATER_ON_ONE_CUP;
        milk -= LATTE_ML_MILK_ON_ONE_CUP;
        coffeeBeans -= LATTE_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private static void createCappuccino() {
        water -= CAPPUCCINO_ML_WATER_ON_ONE_CUP;
        milk -= CAPPUCCINO_ML_MILK_ON_ONE_CUP;
        coffeeBeans -= CAPPUCCINO_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private static void fill() {
        System.out.println("\nWrite how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }

    private static void take() {
        System.out.printf("\nI gave you $%d\n\n", money);
        money = 0;
    }
}
