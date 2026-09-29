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

        String text = "You are in " + currentRoom.getName()
                + "\n" + currentRoom.getDescription();

        for (Item item : currentRoom.getItems()) {
            text += "\n- " + item.getLongName();
        }

        return text;
    }
    public Item takeItem(String shortName) {
        return player.takeItem(shortName);
    }

    public Item dropItem(String shortName) {
        return player.dropItem(shortName);
    }
    // Indsæt inventory()-metoden her

    public String inventory() {
        if (player.getItems().isEmpty()) {
            return "Your inventory is empty.";
        }

        String text = "You are carrying:";

        for (Item item : player.getItems()) {
            text += "\n- " + item.getLongName();
        }

        return text;
    }

}