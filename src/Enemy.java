public class Enemy {
    private String shortName;
    private String longName;
    private String description;
    private int health;
    private Weapon weapon;
    private Room currentRoom;

    public Enemy(String shortName, String longName, String description,
                 int health, Weapon weapon, Room currentRoom) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }

    public String getShortName() { return shortName; }
    public String getLongName() { return longName; }
    public String getDescription() { return description; }
    public int getHealth() { return health; }
    public Weapon getWeapon() { return weapon; }
    public boolean isAlive() { return health > 0; }

    // Fjenden tager selv ansvaret for at doe og droppe sit vaaben.
    public boolean hit(int damage) {
        if (!isAlive()) {
            return true;
        }
        health -= damage;
        if (!isAlive()) {
            currentRoom.addItem(weapon);
            currentRoom.removeEnemy(this);
            return true;
        }
        return false;
    }

    public int attack(Player player) {
        if (!isAlive() || !weapon.canUse()) {
            return 0;
        }
        weapon.use();
        int damage = weapon.getDamage();
        player.hit(damage);
        return damage;
    }
}
