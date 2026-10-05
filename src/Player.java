import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int health = 100;

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public boolean move(String direction) {

        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };

        if (desiredRoom != null) {
            currentRoom = desiredRoom;
            return true;
        } else {
            return false;
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);
    }

    public ArrayList<Item> getItems() {
        return inventory;
    }
    public Item findItem(String shortName) {
        for (Item item : inventory) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }

        return null;
    }
    public Item takeItem(String shortName) {
        Item item = currentRoom.findItem(shortName);

        if (item != null) {
            currentRoom.removeItem(item);
            addItem(item);
        }

        return item;
    }
    public Item dropItem(String shortName) {
        Item item = findItem(shortName);

        if (item != null) {
            removeItem(item);
            currentRoom.addItem(item);
        }

        return item;
    }
    public EatOutcome eat(String shortName) {
        Item item = findItem(shortName);
        boolean foundInInventory = item != null;

        if (item == null) {
            item = currentRoom.findItem(shortName);
        }

        if (item == null) {
            return new EatOutcome(EatResult.NOT_FOUND, null, 0);
        }

        String itemName = item.getLongName();

        if (!(item instanceof Food)) {
            return new EatOutcome(EatResult.NOT_FOOD, itemName, 0);
        }

        Food food = (Food) item;
        int healthChange = food.getHealthPoints();

        health += healthChange;

        if (foundInInventory) {
            removeItem(food);
        } else {
            currentRoom.removeItem(food);
        }

        return new EatOutcome(EatResult.EATEN, itemName, healthChange);
    }
    public DrinkOutcome drink(String shortName) {
        Item item = findItem(shortName);
        boolean foundInInventory = item != null;

        if (item == null) {
            item = currentRoom.findItem(shortName);
        }

        if (item == null) {
            return new DrinkOutcome(DrinkResult.NOT_FOUND, null, 0);
        }

        String itemName = item.getLongName();

        if (!(item instanceof Liquid)) {
            return new DrinkOutcome(DrinkResult.NOT_LIQUID, itemName, 0);
        }

        Liquid liquid = (Liquid) item;
        int healthChange = liquid.getHealthPoints();

        health += healthChange;

        if (foundInInventory) {
            removeItem(liquid);
        } else {
            currentRoom.removeItem(liquid);
        }

        return new DrinkOutcome(DrinkResult.DRUNK, itemName, healthChange);
    }
    public String look() {
        return currentRoom.getFullDescription();
    }

    public String getInventoryDescription() {
        if (inventory.isEmpty()) {
            return "Your inventory is empty.";
        }

        String text = "You are carrying:";

        for (Item item : inventory) {
            text += "\n- " + item.getLongName();
        }

        return text;

    }
    public int getHealth() {
        return health;
    }
}