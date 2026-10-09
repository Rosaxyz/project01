public class AttackOutcome {
    private final AttackResult result;
    private final Weapon playerWeapon;
    private final Enemy enemy;
    private final int damageDealt;
    private final int damageReceived;

    public AttackOutcome(AttackResult result, Weapon playerWeapon, Enemy enemy,
                         int damageDealt, int damageReceived) {
        this.result = result;
        this.playerWeapon = playerWeapon;
        this.enemy = enemy;
        this.damageDealt = damageDealt;
        this.damageReceived = damageReceived;
    }

    public AttackResult getResult() { return result; }
    public Weapon getPlayerWeapon() { return playerWeapon; }
    public Enemy getEnemy() { return enemy; }
    public int getDamageDealt() { return damageDealt; }
    public int getDamageReceived() { return damageReceived; }
}
