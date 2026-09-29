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

    public void showHelp() {

        System.out.println("Available commands:");
        System.out.println("go north / north / n");
        System.out.println("go east / east / e");
        System.out.println("go south / south / s");
        System.out.println("go west / west / w");
        System.out.println("look");
        System.out.println("help");
        System.out.println("exit");
    }
}
