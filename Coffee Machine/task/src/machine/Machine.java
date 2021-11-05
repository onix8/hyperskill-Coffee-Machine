package machine;

public class Machine {
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

    int water;
    int milk;
    int coffeeBeans;
    int cups;
    int money;

    State state;

    public Machine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.money = 550;
        this.state = State.CHOOSING_ACTION;
    }

    public void machineOutput() {
        switch (state) {
            case SHOWING_REMAINDER:
                System.out.println("\nThe coffee machine has:");
                System.out.printf("%d of water\n", water);
                System.out.printf("%d of milk\n", milk);
                System.out.printf("%d of coffee beans\n", coffeeBeans);
                System.out.printf("%d of disposable cups\n", cups);
                System.out.printf("%d of money\n\n", money);

                state = State.CHOOSING_ACTION;

            case EXTRACTING_MONEY:
                if (state == State.EXTRACTING_MONEY) {
                    System.out.printf("\nI gave you $%d\n\n", money);
                    take();

                    state = State.CHOOSING_ACTION;
                }
            case CHOOSING_ACTION:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case CHOOSING_A_VARIANT_OF_COFFEE:
                System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                break;
            case FILLING_WATER:
                System.out.println("\nWrite how many ml of water do you want to add:");
                break;
            case FILLING_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILLING_COFFEE_BEANS:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILLING_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            default:
                break;
        }
    }

    public void userInput(String input) {
        switch (state) {
            case CHOOSING_ACTION:
                mode(input);
                break;
            case CHOOSING_A_VARIANT_OF_COFFEE:
                buy(input);
                state = State.CHOOSING_ACTION;
                break;
            case FILLING_WATER:
            case FILLING_MILK:
            case FILLING_COFFEE_BEANS:
            case FILLING_CUPS:
                fill(Integer.parseInt(input));
                break;
            case EXTRACTING_MONEY:
            default:
                break;
        }
    }

    private void mode(String action) {
        switch (action) {
            case "buy":
                state = State.CHOOSING_A_VARIANT_OF_COFFEE;
                break;
            case "fill":
                state = State.FILLING_WATER;
                break;
            case "take":
                state = State.EXTRACTING_MONEY;
                break;
            case "remaining":
                state = State.SHOWING_REMAINDER;
                break;
            case "exit":
            default:
                break;
        }
    }

    private void buy(String type) {
        if ("back".equals(type)) {
            state = State.CHOOSING_ACTION;
            return;
        }

        if (enoughResources(type)) {
            System.out.println("I have enough resources, making you a coffee!\n");
            switch (type) {
                case "1":
                    money += ESPRESSO_MONEY;
                    createEspresso();
                    break;
                case "2":
                    money += LATTE_MONEY;
                    createLatte();
                    break;
                case "3":
                    money += CAPPUCCINO_MONEY;
                    createCappuccino();
                    break;
                default:
                    break;
            }
        } else {
            System.out.printf("Sorry, not enough %s!\n\n", whatIsMissing(type));
        }
    }

    private boolean enoughResources(String type) {
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

    private void createEspresso() {
        water -= ESPRESSO_ML_WATER_ON_ONE_CUP;
        coffeeBeans -= ESPRESSO_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private void createLatte() {
        water -= LATTE_ML_WATER_ON_ONE_CUP;
        milk -= LATTE_ML_MILK_ON_ONE_CUP;
        coffeeBeans -= LATTE_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private void createCappuccino() {
        water -= CAPPUCCINO_ML_WATER_ON_ONE_CUP;
        milk -= CAPPUCCINO_ML_MILK_ON_ONE_CUP;
        coffeeBeans -= CAPPUCCINO_G_COFFEE_BEANS_ON_ONE_CUP;
        --cups;
    }

    private void fill(int input) {
        switch (state) {
            case FILLING_WATER:
                water += input;
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                milk += input;
                state = State.FILLING_COFFEE_BEANS;
                break;
            case FILLING_COFFEE_BEANS:
                coffeeBeans += input;
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                cups += input;
                state = State.CHOOSING_ACTION;
                break;
            default:
                break;
        }
    }

    private String whatIsMissing(String type) {
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

    private void take() {
        money = 0;
    }
}
