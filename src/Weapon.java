public abstract class Weapon extends Item {

    public Weapon(String shortName, String longName) {
        super(shortName, longName);
    }

    public abstract boolean canUse();

    public abstract void use();

    public abstract String getAttackVerb();

    public abstract String getUsesLeftText();
}