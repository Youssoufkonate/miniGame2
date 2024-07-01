import java.io.*;
import java.util.*;

public class ItemLoader {
    public static void loadItemsFromFile(String filename, Map<String, Room> rooms) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                int itemId = Integer.parseInt(scanner.nextLine().trim());
                String itemName = scanner.nextLine().trim();
                String itemDescription = scanner.nextLine().trim();

                // Place item in corresponding room
                for (Room room : rooms.values()) {
                    if (room.getName().equalsIgnoreCase(itemName)) {
                        room.addItem(new Item(itemId, itemName, itemDescription));
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
}

