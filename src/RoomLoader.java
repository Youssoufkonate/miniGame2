import java.io.*;
import java.util.*;

public class RoomLoader {
    public static Map<String, Room> loadRoomsFromFile(String filename) {
        Map<String, Room> rooms = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isBlank()) {
                    String[] parts = line.split(":");
                    String roomName = parts[0].trim();
                    String roomDescription = parts[1].trim();
                    Room room = new Room(roomName, roomDescription);
                    rooms.put(roomName, room);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }

        // Connect rooms (implement based on your file format)

        return rooms;
    }
}
