package controller;

import model.ItemLoader;
import model.RoomLoader;

import java.util.*;

public class TextAdventureGame {
    private Map<String, Room> rooms;
    public Player player;
    private Scanner scanner;


    public TextAdventureGame(Player pl, Scanner scanner ){
        this.player = pl;;
        this.scanner = scanner;
        initializeGame();
    }


    private void initializeGame() {


       RoomLoader roomLoader = new RoomLoader("room.txt");

        // Create rooms
        Room entrance = new Room("Entrance", "You are standing at the entrance of the castle.");
        Room hall = new Room("Hall", "You are in a grand hall with chandeliers hanging from the ceiling.");
        Room garden = new Room("Garden", "You are in a serene garden with blooming flowers.");

        // Connect rooms
        entrance.setExit("north", hall);
        hall.setExit("south", entrance);
        hall.setExit("east", garden);
        garden.setExit("west", hall);

        // Initialize player with starting room
        player = new Player(entrance); // Assign the starting room to the player

    }

    public void gameplay() {
        if (player != null) {
            Room currentRoom = player.getCurrentRoom();
            System.out.println("You are currently in: " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());

            // Perform other game logic using currentRoom and player
        } else {
            System.out.println("controller.Player is not initialized. Cannot proceed.");

        }
    }

    public TextAdventureGame(String s, String s1) {
    }

    private void initializeGame(String roomsFile, String itemsFile) {
        // Load rooms from file (rooms.txt)
        rooms = RoomLoader.loadRoomsFromFile(roomsFile);

        // Load items from file (items.txt) and place them in rooms
        ItemLoader.loadItemsFromFile(itemsFile, rooms);

        // Set starting room and create player
        Room startingRoom = rooms.get("StartingRoom"); // Adjust this according to your file content
        player = new Player(startingRoom);
    }

    public void play() {
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.println("Type 'commands' for a list of commands.");
        System.out.println("Type 'exit' to end the game. ");
        while (true) {
            Room currentRoom = player.getCurrentRoom();
            currentRoom.setVisited(true);

            // Display room description and available exits
            System.out.println("\n" + currentRoom.getDescription());
            System.out.print("Exits: ");
            for (Object exit : currentRoom.getExits().keySet()) {
                System.out.print(exit + " ");
            }
            System.out.println();

            // Display items in the room
            List<Item> itemsInRoom = currentRoom.getItems();
            if (!itemsInRoom.isEmpty()) {
                System.out.println("Items in the room:");
                for (Item item : itemsInRoom) {
                    System.out.println("- " + item.getName());
                }
            }

            System.out.print("\nWhat would you like to do? ");
            String input = scanner.nextLine().toLowerCase().trim();

            // Process user commands
            if (input.equals("commands")) {
                displayHelp();
            } else if (input.equals("look")) {
                look();
            } else if (input.startsWith("inspect ")) {
                inspectItem(input.substring(8));
            } else if (input.startsWith("get ")) {
                getItem(input.substring(4));
            } else if (input.startsWith("remove ")) {
                removeItem(input.substring(7));
            } else if (input.equals("backpack")) {
                displayInventory();
            } else if (input.equals("exit")) {
                System.out.println("Thank you for playing!");
                break;
            } else {
                System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }

    private void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("- look: Display room description, exits, and items.");
        System.out.println("- inspect <item-name>: Examine an item in the room.");
        System.out.println("- get <item-name>: Add an item to your inventory.");
        System.out.println("- remove <item-name>: Drop an item from your inventory.");
        System.out.println("- backpack: List items in your inventory.");
        System.out.println("- x: Exit the game.");
    }

    private void look() {
        Room currentRoom = player.getCurrentRoom();

        System.out.println("\n" + currentRoom.getDescription());
        System.out.print("Exits: ");
        for (String exit : currentRoom.getExits().keySet()) {
            System.out.print(exit + " ");
        }
        System.out.println();

        List<Item> itemsInRoom = currentRoom.getItems();
        if (!itemsInRoom.isEmpty()) {
            System.out.println("Items in the room:");
            for (Item item : itemsInRoom) {
                if (!player.getInventory().contains(item)) {
                    System.out.println("- " + item.getName());
                }
            }
        }
    }

    private void inspectItem(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        List<Item> itemsInRoom = currentRoom.getItems();
        boolean found = false;

        for (Item item : itemsInRoom) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.getDescription());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("controller.Item '" + itemName + "' not found in the room.");
        }
    }

    private void getItem(String itemName) {
        Room currentRoom = player.getCurrentRoom();
        List<Item> itemsInRoom = currentRoom.getItems();
        Item itemToRemove = null;

        for (Item item : itemsInRoom) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                player.addItemToInventory(item);
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            currentRoom.removeItem(itemToRemove);
            System.out.println(itemName + " has been picked up from the room and added to your inventory.");
        } else {
            System.out.println("controller.Item '" + itemName + "' not found in the room.");
        }
    }

    private void removeItem(String itemName) {
        List<Item> inventory = player.getInventory();
        Item itemToRemove = null;

        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                player.removeItemFromInventory(item);
                player.getCurrentRoom().addItem(item);
                System.out.println(itemName + " has been dropped from your inventory.");
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove == null) {
            System.out.println("controller.Item '" + itemName + "' not found in your inventory.");
        }
    }

    private void displayInventory() {
        List<Item> inventory = player.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You have the following items in your inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }


}
