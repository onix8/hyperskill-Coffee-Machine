package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static final int mlWaterOnOneCup = 200;
    static final int mlMilkOnOneCup = 50;
    static final int gCoffeeBeansOnOneCup = 15;

    public static void main(String[] args) {
        int amountWater;
        int amountMilk;
        int amountCoffeeBeans;
        int quantityCups;

        int requiredAmountCups;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        amountWater = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        amountMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        amountCoffeeBeans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        requiredAmountCups = scanner.nextInt();

        quantityCups = calculateQuantityCups(amountWater, amountMilk, amountCoffeeBeans);

        if (quantityCups == requiredAmountCups) {
            System.out.println("Yes, I can make that amount of coffee");
        }

        if (quantityCups < requiredAmountCups) {
            System.out.printf("No, I can make only %d cup(s) of coffee", quantityCups);
        }

        if (quantityCups > requiredAmountCups) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", quantityCups - requiredAmountCups);
        }
    }

    private static int calculateQuantityCups(int amountWater, int amountMilk, int amountCoffeeBeans) {
        return Math.min(Math.min(amountWater / mlWaterOnOneCup, amountMilk / mlMilkOnOneCup), amountCoffeeBeans / gCoffeeBeansOnOneCup);
    }
}
