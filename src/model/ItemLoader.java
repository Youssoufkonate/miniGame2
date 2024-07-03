package model;

import controller.Room;

import java.io.*;
import java.util.*;


//GameController function  --> change the class name from ItemLoader to ItemDB
public class ItemLoader {


    //create Map<String, Item>  type variable.    --> after you create this variable, you can create getter method.

    //make sure create ItemLoader or ItemDB constructor.
    //make this constructor to invoke loadItemsFromFile() methods.



    public static void loadItemsFromFile(String filename, Map<String, Room> rooms) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                int itemId = Integer.parseInt(scanner.nextLine().trim());
                String itemName = scanner.nextLine().trim();
                String itemDescription = scanner.nextLine().trim();

                //take this out if you want.
                // Place item in corresponding room
//                for (Room room : rooms.values()) {
//                    if (room.getName().equalsIgnoreCase(itemName)) {
//                        room.addItem(new Item(itemId, itemName, itemDescription));
//                        break;
//                    }
//                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }
}

