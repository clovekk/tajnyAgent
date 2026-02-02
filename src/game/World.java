package game;

import Commands.*;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private Room currentRoom;
    private ArrayList<String> currentTasks;
    private ArrayList<String> futureTasks;
    private ArrayList<Item> items;
    private ArrayList<Character> characters;
    private ArrayList<Room> rooms;
    private int time;
    private int gameState;
    private Player player;
    private ArrayList<String> hints;
    private boolean end;

    public World(Room currentRoom, ArrayList<String> currentTasks, ArrayList<String> futureTasks, ArrayList<Item> items, ArrayList<Character> characters, ArrayList<Room> rooms, int time, int gameState, Player player, ArrayList<String> hints, HashMap<String, Command> commands, boolean end) {
        this.currentRoom = currentRoom;
        this.currentTasks = currentTasks;
        this.futureTasks = futureTasks;
        this.items = items;
        this.characters = characters;
        this.rooms = rooms;
        this.time = time;
        this.gameState = gameState;
        this.player = player;
        this.hints = hints;
        this.end = end;
    }

    public World() {
        this.currentRoom = null;
        this.currentTasks = new ArrayList<>();
        this.futureTasks = new ArrayList<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.time = 0;
        this.gameState = 0;
        this.player = new Player();
        this.hints = new ArrayList<>();
        this.end = false;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public ArrayList<String> getCurrentTasks() {
        return currentTasks;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public int getTime() {
        return time;
    }
    public Player getPlayer() {
        return player;
    }
    public ArrayList<String> getHints() {
        return hints;
    }
    public boolean isEnd() {
        return end;
    }
    public ArrayList<String> getFutureTasks() {
        return futureTasks;
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public ArrayList<Character> getCharacters() {
        return characters;
    }
    public int getGameState() {
        return gameState;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    public void setCurrentTasks(ArrayList<String> currentTasks) {
        this.currentTasks = currentTasks;
    }
    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setHints(ArrayList<String> hints) {
        this.hints = hints;
    }
    public void setEnd(boolean end) {
        this.end = end;
    }
    public void setFutureTasks(ArrayList<String> futureTasks) {
        this.futureTasks = futureTasks;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public Item getItem(String itemID) {
        for (Item item : this.items) {
            if (item.getId().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    public Room getRoom(String roomID) {
        for (Room room : this.rooms) {
            if (room.getId().equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    public Character getCharacter(String characterID) {
        for (Character character : this.characters) {
            if (character.getId().equals(characterID)) {
                return character;
            }
        }
        return null;
    }

    public Item getItemByName(String itemName) {
        for (Item item : this.items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Room getRoomByName(String roomName) {
        for (Room room : this.rooms) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room;
            }
        }
        return null;
    }

    public Character getCharacterByName(String characterName) {
        for (Character character : this.characters) {
            if (character.getName().equalsIgnoreCase(characterName)) {
                return character;
            }
        }
        return null;
    }

    public Command readCommand(String command) {
        return null;
    }

    @Override
    public String toString() {
        return "World{" +
                "currentRoom=" + currentRoom +
                ", currentTasks=" + currentTasks +
                ", futureTasks=" + futureTasks +
                ", items=" + items +
                ", characters=" + characters +
                ", rooms=" + rooms +
                ", time=" + time +
                ", gameState=" + gameState +
                ", player=" + player +
                ", hints=" + hints +
                ", end=" + end +
                '}';
    }
}
