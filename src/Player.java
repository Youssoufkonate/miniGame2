import java.util.*;

public class Player {
    private Room currentRoom;
    private List<Item> inventory;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = currentRoom;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public boolean removeItemFromInventory(Item item) {
        return inventory.remove(item);
    }

    public List<Item> getInventory() {
        return inventory;
    }
}
