import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;
    private Adventure adventure;

    public UserInterface() {
        scanner = new Scanner(System.in);
        adventure = new Adventure();
    }

    public void startProgram() {

        adventure.startGame();

        System.out.println("Welcome to Adventure!");
        System.out.println();
        System.out.println(adventure.look());

        while (true) {

            System.out.print("> ");
            String command = scanner.nextLine().toLowerCase().trim();

            if (command.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            else if (command.equals("help")) {
                showHelp();
            }

            else if (command.equals("look")) {
                System.out.println(adventure.look());
            }
            else if (command.equals("health")) {
                showHealth();
            }
            else if (command.equals("inventory") || command.equals("inv")) {
                System.out.println(adventure.inventory());
            }
            else if (command.startsWith("take ")) {
                String itemName = command.substring(5).trim();
                Item item = adventure.takeItem(itemName);

                if (item != null) {
                    System.out.println("You have taken " + item.getLongName());
                } else {
                    System.out.println("There is nothing like " + itemName
                            + " to take around here");
                }
            }
            else if (command.startsWith("drop ")) {
                String itemName = command.substring(5).trim();
                Item item = adventure.dropItem(itemName);

                if (item != null) {
                    System.out.println("You have dropped " + item.getLongName());
                } else {
                    System.out.println("You don't have anything like " + itemName
                            + " in your inventory");
                }
            }
            else if (command.startsWith("eat ")) {
                String itemName = command.substring(4).trim();
                eatItem(itemName);
            }
            else if (command.startsWith("drink ")) {
                String itemName = command.substring(6).trim();
                drinkItem(itemName);
            }
            else if (command.startsWith("equip ")) {
                String itemName = command.substring(6).trim();
                equipItem(itemName);
            }
            else if (command.equals("attack")) {
                attack();
            }
            else {
                parseInput(command);
            }
        }

        scanner.close();
    }

    public void parseInput(String command) {

        String direction = null;

        if (command.equals("go north")
                || command.equals("north")
                || command.equals("n")
                || command.equals("go n")) {

            direction = "north";
        }

        else if (command.equals("go east")
                || command.equals("east")
                || command.equals("e")
                || command.equals("go e")) {

            direction = "east";
        }

        else if (command.equals("go south")
                || command.equals("south")
                || command.equals("s")
                || command.equals("go s")) {

            direction = "south";
        }

        else if (command.equals("go west")
                || command.equals("west")
                || command.equals("w")
                || command.equals("go w")) {

            direction = "west";
        }

        else {
            System.out.println("I don't understand that command.");
            return;
        }

        boolean moved = adventure.go(direction);

        if (moved) {
            System.out.println(adventure.look());
        } else {
            System.out.println("You cannot go that way");
        }
    }
    private void eatItem(String itemName) {
        EatOutcome outcome = adventure.eat(itemName);

        switch (outcome.getResult()) {
            case NOT_FOUND -> {
                System.out.println(
                        "There is nothing like " + itemName
                                + " to eat around here"
                );
            }

            case NOT_FOOD -> {
                System.out.println(
                        "You cannot eat the " + outcome.getItemName()
                );
            }

            case EATEN -> {
                String message = "You eat the " + outcome.getItemName() + ".";

                if (outcome.getHealthChange() > 0) {
                    message += " You feel a little better.";
                } else if (outcome.getHealthChange() < 0) {
                    message += " That was a mistake.";
                } else {
                    message += " Your health stays the same.";
                }

                System.out.println(message);
            }
        }
    }
    private void drinkItem(String itemName) {
        DrinkOutcome outcome = adventure.drink(itemName);

        switch (outcome.getResult()) {
            case NOT_FOUND -> {
                System.out.println(
                        "There is nothing like " + itemName
                                + " to drink around here"
                );
            }

            case NOT_LIQUID -> {
                System.out.println(
                        "You cannot drink the " + outcome.getItemName()
                );
            }

            case DRUNK -> {
                String message = "You drink the " + outcome.getItemName() + ".";

                if (outcome.getHealthChange() > 0) {
                    message += " You feel a little better.";
                } else if (outcome.getHealthChange() < 0) {
                    message += " That was a mistake.";
                } else {
                    message += " Your health stays the same.";
                }

                System.out.println(message);
            }
        }
    }
    private void equipItem(String itemName) {
        EquipResult result = adventure.equip(itemName);

        switch (result) {
            case NOT_FOUND -> {
                System.out.println(
                        "You don't have anything like " + itemName
                                + " in your inventory"
                );
            }

            case NOT_WEAPON -> {
                System.out.println(
                        "The item '" + itemName + "' is not a weapon"
                );
            }

            case EQUIPPED -> {
                Weapon weapon = adventure.getEquippedWeapon();

                System.out.println(
                        "You have equipped " + weapon.getLongName()
                );
            }
        }
    }

    private void attack() {
        AttackResult result = adventure.attack();

        switch (result) {
            case NO_WEAPON -> {
                System.out.println("You don't have a weapon equipped");
            }

            case CANNOT_USE -> {
                System.out.println(
                        "Your equipped weapon is out of ammunition"
                );
            }

            case ATTACKED -> {
                Weapon weapon = adventure.getEquippedWeapon();

                String message = "You " + weapon.getAttackVerb()
                        + " " + weapon.getLongName()
                        + " at the empty air.";

                String usesLeftText = weapon.getUsesLeftText();

                if (!usesLeftText.isEmpty()) {
                    message += " " + usesLeftText;
                }

                System.out.println(message);
            }
        }
    }
    private void showHealth() {
        int health = adventure.getHealth();
        String description;

        if (health >= 100) {
            description = "you are in perfect health";
        } else if (health >= 50) {
            description = "you are in good health, but avoid fighting right now";
        } else if (health >= 25) {
            description = "you are wounded - find something healthy to eat";
        } else if (health >= 1) {
            description = "you are barely alive";
        } else {
            description = "you should be dead";
        }

        System.out.println("health: " + health + " - " + description);
    }

    public void showHelp() {

        System.out.println("Available commands:");
        System.out.println("go north/north/n");
        System.out.println("go east/east/e");
        System.out.println("go south/south/s");
        System.out.println("go west/west/w");
        System.out.println("look");
        System.out.println("help");
        System.out.println("exit");
        System.out.println("take <item>");
        System.out.println("drop <item>");
        System.out.println("inventory/inv");
        System.out.println("health");
        System.out.println("eat <food>");
        System.out.println("drink <liquid>");
        System.out.println("equip <weapon>");
        System.out.println("attack");
    }
}
