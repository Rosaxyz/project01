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
                "You have discovered a hidden chamber. A mysterious blue light shines from the center of the room."
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

        startRoom = room1;
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
