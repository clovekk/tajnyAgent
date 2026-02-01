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
    private Player player;
    private ArrayList<String> hints;
    private HashMap<String, Command> commands;
    private boolean end;

    public World(Room currentRoom, ArrayList<String> currentTasks, ArrayList<String> futureTasks, ArrayList<Item> items, ArrayList<Character> characters, ArrayList<Room> rooms, int time, Player player, ArrayList<String> hints, HashMap<String, Command> commands, boolean end) {
        this.currentRoom = currentRoom;
        this.currentTasks = currentTasks;
        this.futureTasks = futureTasks;
        this.items = items;
        this.characters = characters;
        this.rooms = rooms;
        this.time = time;
        this.player = player;
        this.hints = hints;
        this.commands = commands;
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
        this.player = new Player();
        this.hints = new ArrayList<>();
        this.commands = new HashMap<>();
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
    public HashMap<String, Command> getCommands() {
        return commands;
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
    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
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
                ", player=" + player +
                ", hints=" + hints +
                ", commands=" + commands +
                ", end=" + end +
                '}';
    }
}
