package game;

import Commands.*;

import java.text.Normalizer;
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

    public ArrayList<Item> getCurrentRoomItems() {
        ArrayList<Item> currentItems = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getItemsID().size(); i++) {
            currentItems.add(this.getItem(this.getCurrentRoom().getItemsID().get(i)));
        }
        return currentItems;
    }

    public ArrayList<Character> getCurrentRoomCharacters() {
        ArrayList<Character> currentCharacters = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getCharactersID().size(); i++) {
            currentCharacters.add(this.getCharacter(this.getCurrentRoom().getCharactersID().get(i)));
        }
        return currentCharacters;
    }

    public ArrayList<String> getCurrentRoomItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getItemsID().size(); i++) {
            itemNames.add(this.getItem(this.getCurrentRoom().getItemsID().get(i)).getName());
        }
        return itemNames;
    }

    public ArrayList<String> getCurrentRoomCharacterNames() {
        ArrayList<String> characterNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getCharactersID().size() - 1; i++) {
            characterNames.add(this.getCharacter(this.getCurrentRoom().getCharactersID().get(i)).getName());
        }
        return characterNames;
    }

    public boolean hasMandatoryTalk(Room room) {
        boolean hasMandatoryTalk = false;
        for (int i = 0; i < this.getCurrentRoom().getCharactersID().size() - 1; i++) {
            if (this.getCharacter(this.getCurrentRoom().getCharactersID().get(i)).isMandatoryTalk()) {
                hasMandatoryTalk = true;
            }
        }
        return hasMandatoryTalk;
    }

    public ArrayList<Item> getCurrentRoomFoundItems() {
        ArrayList<Item> currentFoundItems = this.getCurrentRoomItems();
        for (int i = 0; i < currentFoundItems.size(); i++) {
            if (currentFoundItems.get(i).getState() != 2) {
                currentFoundItems.remove((int)i);
            }
        }
        return currentFoundItems;
    }

    public ArrayList<String> getCurrentRoomFoundItemNames() {
        ArrayList<String> currentFoundItemNames = new ArrayList<>();
        for (int i = 0; i < getCurrentRoomFoundItems().size(); i++) {
            currentFoundItemNames.add(getCurrentRoomFoundItems().get(i).getName());
        }
        return currentFoundItemNames;
    }

    public String currentFoundItemsToString() {
        if (!getCurrentRoomFoundItemNames().isEmpty()) {
            return "Předměty v místnosti: " + getCurrentRoomFoundItemNames() + "\n";
        } else {
            return "";
        }
    }

    public Room getRoomByCompatibleName(String roomName) {
        for (Room room : this.rooms) {
            String compatibleName = Normalizer.normalize(room.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(roomName)) {
                return room;
            }
        }
        return null;
    }

    public Character getCharacterByCompatibleName(String characterName) {
        for (Character character : this.characters) {
            String compatibleName = Normalizer.normalize(character.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(characterName)) {
                return character;
            }
        }
        return null;
    }

    public Item getItemByCompatibleName(String itemName) {
        for (Item item : this.items) {
            String compatibleName = Normalizer.normalize(item.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public boolean roomWithCompatibleNameExists(String name) {
        for (Room room : this.rooms) {
            String compatibleName = Normalizer.normalize(room.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean itemWithCompatibleNameExists(String name) {
        for (Item item : this.items) {
            String compatibleName = Normalizer.normalize(item.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> adjacentRoomCompatibleNames(String name) {
        ArrayList<String> adjacentRoomNames = new ArrayList<>();
        for (int i = 0; i < this.currentRoom.getAdjacentRoomsID().size(); i++) {
            String adjacentRoomName = Normalizer.normalize(this.currentRoom.getAdjacentRoomsID().get(i), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            adjacentRoomNames.add(adjacentRoomName);
        }
        return adjacentRoomNames;
    }

    public Room findRoomWithCharacter(String characterID) {
        for (Room room : this.rooms) {
            if (room.getCharactersID().contains(characterID)) {
                return room;
            }
        }
        return null;
    }

    public void moveCharacter(String characterID, String roomID) {
        if (this.getCharacters().contains(getCharacter(characterID)) && this.getRooms().contains(getRoom(roomID))) {
            Character character = getCharacter(characterID);
            Room room = getRoom(roomID);
            findRoomWithCharacter(characterID).getCharactersID().remove(characterID);
            room.getCharactersID().add(characterID);
        }
    }

    public ArrayList<String> getCurrentAdjacentRooms() {
        ArrayList<String> adjacentRoomNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getAdjacentRoomsID().size(); i++) {
            adjacentRoomNames.add(this.getRoom(this.getCurrentRoom().getAdjacentRoomsID().get(i)).getName());
        }
        return adjacentRoomNames;
    }

    public ArrayList<String> getRoomsID() {
        ArrayList<String> roomsID = new ArrayList<>();
        for (Room room : this.rooms) {
            roomsID.add(room.getId());
        }
        return roomsID;
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
