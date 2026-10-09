import java.util.ArrayList;

public class Room {

    private String name;
    private String description;

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Enemy northGuard;

    private Room north;
    private Room east;
    private Room south;
    private Room west;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getEast() {
        return east;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public void setWest(Room west) {
        this.west = west;
    }

// Her starter de tre nye metoder:

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item findItem(String shortName) {
        for (Item item : items) {
            if (item.getShortName().equals(shortName)) {
                return item;
            }
        }

        return null;
    }
    public String getFullDescription() {
        String text = "You are in " + name + "\n" + description;

        for (Item item : items) {
            text += "\n- " + item.getLongName();
        }

        for (Enemy enemy : enemies) {
            text += "\nBeware! Here lurks: " + enemy.getLongName();
            text += "\n" + enemy.getDescription();
        }
        return text;
    }

    public void addEnemy(Enemy enemy) { enemies.add(enemy); }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        if (enemy == northGuard) {
            northGuard = null;
        }
    }

    public ArrayList<Enemy> getEnemies() { return enemies; }

    public Enemy findEnemy(String shortName) {
        for (Enemy enemy : enemies) {
            if (enemy.getShortName().equals(shortName)) {
                return enemy;
            }
        }
        return null;
    }

    public void setNorthGuard(Enemy enemy) { northGuard = enemy; }

    public boolean isNorthBlocked() {
        return northGuard != null && northGuard.isAlive();
    }

}
// Denne afslutter hele Room-klassen
