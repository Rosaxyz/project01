import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();
    private int health = 100;
    private Weapon equippedWeapon;

    public Player(Room startRoom) {
        currentRoom = startRoom;
    }

    public MoveResult move(String direction) {
        Room desiredRoom = switch (direction) {
            case "north" -> currentRoom.getNorth();
            case "south" -> currentRoom.getSouth();
            case "east" -> currentRoom.getEast();
            case "west" -> currentRoom.getWest();
            default -> null;
        };
        if (desiredRoom == null) {
            return MoveResult.NO_EXIT;
        }
        if (direction.equals("north") && currentRoom.isNorthBlocked()) {
            return MoveResult.BLOCKED;
        }
        currentRoom = desiredRoom;
        return MoveResult.MOVED;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void addItem(Item item) {
        inventory.add(item);
    }

    public void removeItem(Item item) {
        inventory.remove(item);

        if (item == equippedWeapon) {
            equippedWeapon = null;
        }
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

        if (equippedWeapon != null) {
            text += "\nEquipped: " + equippedWeapon.getLongName();
        }

        return text;
    }
    public int getHealth() {
        return health;
    }
    public EquipResult equip(String shortName) {
        Item item = findItem(shortName);

        if (item == null) {
            return EquipResult.NOT_FOUND;
        }

        if (!(item instanceof Weapon)) {
            return EquipResult.NOT_WEAPON;
        }

        equippedWeapon = (Weapon) item;

        return EquipResult.EQUIPPED;
    }

    public AttackOutcome attack(String enemyName) {
        if (equippedWeapon == null) {
            return new AttackOutcome(AttackResult.NO_WEAPON, null, null, 0, 0);
        }
        if (!equippedWeapon.canUse()) {
            return new AttackOutcome(AttackResult.CANNOT_USE, equippedWeapon, null, 0, 0);
        }

        // Find maalet foer use(), saa et forkert navn aldrig koster et skud.
        Enemy enemy = null;
        if (!enemyName.isEmpty()) {
            enemy = currentRoom.findEnemy(enemyName);
            if (enemy == null) {
                return new AttackOutcome(AttackResult.ENEMY_NOT_FOUND, equippedWeapon, null, 0, 0);
            }
        } else if (!currentRoom.getEnemies().isEmpty()) {
            enemy = currentRoom.getEnemies().get(0);
        }

        equippedWeapon.use();
        if (enemy == null) {
            return new AttackOutcome(AttackResult.AIR_ATTACKED, equippedWeapon, null, 0, 0);
        }

        int damageDealt = equippedWeapon.getDamage();
        boolean enemyDied = enemy.hit(damageDealt);
        if (enemyDied) {
            return new AttackOutcome(AttackResult.ENEMY_DIED, equippedWeapon, enemy, damageDealt, 0);
        }
        if (!enemy.getWeapon().canUse()) {
            return new AttackOutcome(AttackResult.ENEMY_CANNOT_ATTACK, equippedWeapon, enemy, damageDealt, 0);
        }
        int damageReceived = enemy.attack(this);
        return new AttackOutcome(AttackResult.ENEMY_HIT, equippedWeapon, enemy, damageDealt, damageReceived);
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public boolean isAlive() { return health > 0; }

    public boolean hit(int damage) {
        health -= damage;
        return !isAlive();
    }

}
