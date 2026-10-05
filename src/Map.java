public class Map {

    private Room startRoom;

    public void buildMap() {

        Room room1 = new Room(
                "Room 1",
                "You stand at the entrance of an old abandoned castle. The air is cold and the walls are covered with dust."
        );

        Room room2 = new Room(
                "Room 2",
                "You are in a dark hallway. Water drips slowly from the ceiling and echoes through the castle."
        );

        Room room3 = new Room(
                "Room 3",
                "You enter a small stone chamber. Strange symbols have been carved into the walls."
        );

        Room room4 = new Room(
                "Room 4",
                "You are in an old library. Broken books and pieces of wood are scattered across the floor."
        );

        Room room5 = new Room(
                "Room 5",
                "You have discovered a hidden chamber. A mysterious purple light shines from the center of the room."
        );

        Room room6 = new Room(
                "Room 6",
                "You are inside a cold tower room. Wind enters through cracks in the ancient stone walls."
        );

        Room room7 = new Room(
                "Room 7",
                "You enter an abandoned storage room. Old boxes and broken furniture fill the corners."
        );

        Room room8 = new Room(
                "Room 8",
                "You are in a large underground hall. The floor is covered with dust and old footprints."
        );

        Room room9 = new Room(
                "Room 9",
                "You enter a quiet chamber. A small ray of light shines through a crack in the wall."
        );

        room1.setEast(room2);
        room2.setWest(room1);

        room2.setEast(room3);
        room3.setWest(room2);

        room1.setSouth(room4);
        room4.setNorth(room1);

        room3.setSouth(room6);
        room6.setNorth(room3);

        room4.setSouth(room7);
        room7.setNorth(room4);

        room5.setSouth(room8);
        room8.setNorth(room5);

        room6.setSouth(room9);
        room9.setNorth(room6);

        room7.setEast(room8);
        room8.setWest(room7);

        room8.setEast(room9);
        room9.setWest(room8);

        Item torch = new Item("torch", "an old wooden torch");
        room1.addItem(torch);
        startRoom = room1;

        Item helmet = new Item("helmet", "an old knight's helmet");
        room2.addItem(helmet);

        Item amulet = new Item("amulet", "an ancient mysterious amulet");
        room3.addItem(amulet);

        Item book = new Item("book", "a dusty old book");
        room4.addItem(book);

        Item crystal = new Item("crystal", "a glowing purple crystal");
        room5.addItem(crystal);
        Item rose = new Item("rose", "a deep crimson rose with silver thorns");
        room5.addItem(rose);
        Item crown = new Item("crown", "a black crown with crimson gemstones");
        room5.addItem(crown);

        Item shield = new Item("shield", "an ancient shield with a blood-red crest");
        room6.addItem(shield);

        Item key = new Item("key","a mysterious shiney key");
        room7.addItem(key);

        Item sword = new Item("sword", "a rusty old sword");
        room8.addItem(sword);

        Item goblet = new Item("goblet", "an ancient silver goblet");
        room9.addItem(goblet);

        Food cake = new Food("cake", "a slice of dark chocolate cake with crimson cherries", 10);
        room3.addItem(cake);

        Food mushroom = new Food("mushroom", "a poisonous crypt mushroom", -50);
        room4.addItem(mushroom);

        Liquid holywater = new Liquid(
                "holy water",
                "a vial of holy water",
                100
        );
        room5.addItem(holywater);

        Liquid elixir = new Liquid(
                "elixir",
                "a mysterious green elixir",
                -99);
        room9.addItem(elixir);
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
