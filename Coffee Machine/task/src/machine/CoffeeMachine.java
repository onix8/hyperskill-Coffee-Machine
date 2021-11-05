package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int quantityCups;
        int mlWaterOnOneCup = 200;
        int mlMilkOnOneCup = 50;
        int gCoffeeBeansOnOneCup = 15;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many cups of coffee you will need:");
        quantityCups = scanner.nextInt();
        System.out.printf("For %d cups of coffee you will need:\n", quantityCups);
        System.out.printf("%d ml of water\n", mlWaterOnOneCup * quantityCups);
        System.out.printf("%d ml of milk\n", mlMilkOnOneCup * quantityCups);
        System.out.printf("%d g of coffee beans\n", gCoffeeBeansOnOneCup * quantityCups);
    }
}
