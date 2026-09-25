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
        Room currentRoom = player.getCurrentRoom();

        return "You are in " + currentRoom.getName()
                + "\n" + currentRoom.getDescription();
    }
}
