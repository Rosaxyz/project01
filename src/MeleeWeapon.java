public class MeleeWeapon extends Weapon {

    public MeleeWeapon(String shortName, String longName, int damage) {
        super(shortName, longName, damage);
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void use() {
        // Et nærkampsvåben bruger ikke ammunition.
    }

    @Override
    public String getAttackVerb() {
        return "swing";
    }

    @Override
    public String getUsesLeftText() {
        return "";
    }
}
