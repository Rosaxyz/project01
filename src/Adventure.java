 import java.util.Scanner;

    public class Adventure {

        private Room currentRoom;
        private Scanner scanner;

        public Adventure() {

            scanner = new Scanner(System.in);

            // -------------------------
            // CREATE THE 9 ROOMS
            // -------------------------

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


            // -------------------------
            // CONNECT THE ROOMS
            // -------------------------

            // Room 1 <-> Room 2
            room1.setEast(room2);
            room2.setWest(room1);

            // Room 2 <-> Room 3
            room2.setEast(room3);
            room3.setWest(room2);


            // Room 1 <-> Room 4
            room1.setSouth(room4);
            room4.setNorth(room1);

            // Room 3 <-> Room 6
            room3.setSouth(room6);
            room6.setNorth(room3);


            // Room 4 <-> Room 7
            room4.setSouth(room7);
            room7.setNorth(room4);

            // Room 5 <-> Room 8
            room5.setSouth(room8);
            room8.setNorth(room5);

            // Room 6 <-> Room 9
            room6.setSouth(room9);
            room9.setNorth(room6);


            // Room 7 <-> Room 8
            room7.setEast(room8);
            room8.setWest(room7);

            // Room 8 <-> Room 9
            room8.setEast(room9);
            room9.setWest(room8);


            // -------------------------
            // STARTING ROOM
            // -------------------------

            currentRoom = room1;
        }


        // -------------------------
        // START THE GAME
        // -------------------------

        public void start() {

            System.out.println("Welcome to Adventure!");
            System.out.println();

            showCurrentRoom();

            while (true) {

                System.out.print("> ");

                String command = scanner.nextLine().toLowerCase().trim();

                if (command.equals("go north")
                        || command.equals("north")
                        || command.equals("n")
                        || command.equals("go n")) {

                    moveNorth();
                }

                else if (command.equals("go east")
                        || command.equals("east")
                        || command.equals("e")
                        || command.equals("go e")) {

                    moveEast();
                }

                else if (command.equals("go south")
                        || command.equals("south")
                        || command.equals("s")
                        || command.equals("go s")) {

                    moveSouth();
                }

                else if (command.equals("go west")
                        || command.equals("west")
                        || command.equals("w")
                        || command.equals("go w")) {

                    moveWest();
                }

                else if (command.equals("look")) {
                    showCurrentRoom();
                }

                else if (command.equals("help")) {
                    showHelp();
                }

                else if (command.equals("exit")) {
                    System.out.println("Goodbye!");
                    break;
                }

                else {
                    System.out.println("I don't understand that command.");
                }
            }

            scanner.close();
        }


        // -------------------------
        // SHOW CURRENT ROOM
        // -------------------------

        private void showCurrentRoom() {

            System.out.println("You are in " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            System.out.println();
        }


        // -------------------------
        // HELP
        // -------------------------

        private void showHelp() {

            System.out.println("Available commands:");
            System.out.println("go north / north / n");
            System.out.println("go east / east / e");
            System.out.println("go south / south / s");
            System.out.println("go west / west / w");
            System.out.println("look");
            System.out.println("help");
            System.out.println("exit");
        }


        // -------------------------
        // MOVE NORTH
        // -------------------------

        private void moveNorth() {

            Room nextRoom = currentRoom.getNorth();

            if (nextRoom != null) {

                currentRoom = nextRoom;
                showCurrentRoom();

            } else {

                System.out.println("You cannot go that way");
            }
        }


        // -------------------------
        // MOVE EAST
        // -------------------------

        private void moveEast() {

            Room nextRoom = currentRoom.getEast();

            if (nextRoom != null) {

                currentRoom = nextRoom;
                showCurrentRoom();

            } else {

                System.out.println("You cannot go that way");
            }
        }


        // -------------------------
        // MOVE SOUTH
        // -------------------------

        private void moveSouth() {

            Room nextRoom = currentRoom.getSouth();

            if (nextRoom != null) {

                currentRoom = nextRoom;
                showCurrentRoom();

            } else {

                System.out.println("You cannot go that way");
            }
        }


        // -------------------------
        // MOVE WEST
        // -------------------------

        private void moveWest() {

            Room nextRoom = currentRoom.getWest();

            if (nextRoom != null) {

                currentRoom = nextRoom;
                showCurrentRoom();

            } else {

                System.out.println("You cannot go that way");
            }
        }
    }
