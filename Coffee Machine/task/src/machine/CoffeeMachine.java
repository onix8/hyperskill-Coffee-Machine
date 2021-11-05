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
        state();

        System.out.println("\nWrite action (buy, fill, take):");
        String user = scanner.nextLine();
        mode(user);

        System.out.println();
        state();
    }

    private static void state() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", coffeeBeans);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("%d of money\n", money);
    }

    private static void mode(String user) {
        switch (user) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
        }
    }

    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int type = scanner.nextInt();
        switch (type) {
            case 1:
                money += ESPRESSO_MONEY;
                createEspresso();
                break;
            case 2:
                money += LATTE_MONEY;
                createLatte();
                break;
            case 3:
                money += CAPPUCCINO_MONEY;
                createCappuccino();
                break;
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
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }

    private static void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }
}
