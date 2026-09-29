import java.util.ArrayList;

public class Room {

        private String name;
        private String description;

    private ArrayList<Item> items = new ArrayList<>();

        private Room north;
        private Room east;
        private Room south;
        private Room west;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Room getNorth() {
            return north;
        }

        public Room getEast() {
            return east;
        }

        public Room getSouth() {
            return south;
        }

        public Room getWest() {
            return west;
        }

        public void setNorth(Room north) {
            this.north = north;
        }

        public void setEast(Room east) {
            this.east = east;
        }

        public void setSouth(Room south) {
            this.south = south;
        }

        public void setWest(Room west) {
            this.west = west;
        }

// Her starter de tre nye metoder:

        public void addItem(Item item) {
            items.add(item);
        }
        public void removeItem(Item item) {
            items.remove(item);
        }
        public ArrayList<Item> getItems() {
            return items;
        }
        }
// Denne afslutter hele Room-klassen