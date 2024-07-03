package view;

import controller.TextAdventureGame;

public class GameUI {


    public static void main(String[] args) {

      //you cannot retrieve data from db (rooms.txt) direction from UI class.
        TextAdventureGame game = new TextAdventureGame("rooms.txt", "items.txt");
        game.Gameplay();
    }
}
