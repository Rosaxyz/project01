public class DrinkOutcome {

    private final DrinkResult result;
    private final String itemName;
    private final int healthChange;

    public DrinkOutcome(DrinkResult result, String itemName, int healthChange) {
        this.result = result;
        this.itemName = itemName;
        this.healthChange = healthChange;
    }

    public DrinkResult getResult() {
        return result;
    }

    public String getItemName() {
        return itemName;
    }

    public int getHealthChange() {
        return healthChange;
    }
}