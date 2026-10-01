import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

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
}