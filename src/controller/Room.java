package controller;

import java.util.*;

public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private boolean visited;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
        this.visited = false;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Method to set exits for the room
    public void setExit(String direction, Room neighbor) {
        exits.put(direction.toLowerCase(), neighbor);
    }

    // Method to retrieve neighboring room based on direction
    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }

    // Method to add items to the room
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to remove items from the room
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    // Method to retrieve list of items in the room
    public List<Item> getItems() {
        return items;
    }

    public Map<String, Room> getExits() {
        return exits;
    }
}
