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

    public boolean go(String direction) {
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
}