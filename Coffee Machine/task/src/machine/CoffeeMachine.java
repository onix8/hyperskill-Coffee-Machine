package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Machine machine = new Machine();
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            machine.machineOutput();
            input = scanner.nextLine();
            machine.userInput(input);
        } while (!"exit".equals(input));
    }
}
