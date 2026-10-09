public class Adventure {

    private Player player;
    private Map map;

    public Adventure() {
        map = new Map();
    }

    public void startGame() {
        map.buildMap();
        player = new Player(map.getStartRoom());
    }

    public MoveResult go(String direction) {
        return player.move(direction);
    }

    public String look() {
        return player.look();
    }

    public Item takeItem(String shortName) {
        return player.takeItem(shortName);
    }

    public Item dropItem(String shortName) {
        return player.dropItem(shortName);
    }

    public String inventory() {
        return player.getInventoryDescription();
    }
    public int getHealth() {
        return player.getHealth();
    }
    public EatOutcome eat(String shortName) {
        return player.eat(shortName);
    }
    public DrinkOutcome drink(String shortName) {
        return player.drink(shortName);
    }
    public EquipResult equip(String shortName) {
        return player.equip(shortName);
    }

    public AttackOutcome attack(String enemyName) {
        return player.attack(enemyName);
    }

    public Weapon getEquippedWeapon() {
        return player.getEquippedWeapon();
    }

    public boolean isPlayerAlive() { return player.isAlive(); }

}
