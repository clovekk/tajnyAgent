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

    /**
     * Finds the item with the corresponding itemID, the world must contain this item in order to be able to reach it
     * @param itemID the ID of the item in a String form
     * @return the item that has this itemID, ensure that every item has a unique ID
     */
    public Item getItem(String itemID) {
        for (Item item : this.items) {
            if (item.getId().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Finds the room with the corresponding roomID, the world must contain this room in order to be able to reach it
     * @param roomID the ID of the room in a String form
     * @return the room that has this roomID, ensure that every room has a unique ID
     */
    public Room getRoom(String roomID) {
        for (Room room : this.rooms) {
            if (room.getId().equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Finds the character with the corresponding characterID, the world must contain this character in order to be able to reach it
     * @param characterID the ID of the character in a String form
     * @return the character that has this characterID, ensure that every character has a unique ID
     */
    public Character getCharacter(String characterID) {
        for (Character character : this.characters) {
            if (character.getId().equals(characterID)) {
                return character;
            }
        }
        return null;
    }

    //old methods that can only find the element by its precise name, replaced with the get____ByCompatibleName() methods
    /*
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
    */

    /**
     * Finds all the items in the currentRoom and puts them into an ArrayList
     * @return an ArrayList of all the items in the currentRoom
     */
    public ArrayList<Item> getCurrentRoomItems() {
        ArrayList<Item> currentItems = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getItemsID().size(); i++) {
            currentItems.add(this.getItem(this.getCurrentRoom().getItemsID().get(i)));
        }
        return currentItems;
    }

    /**
     * Finds all the characters in the currentRoom and puts them into an ArrayList
     * @return an ArrayList of all the characters in the currentRoom
     */
    public ArrayList<Character> getCurrentRoomCharacters() {
        ArrayList<Character> currentCharacters = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getCharactersID().size(); i++) {
            currentCharacters.add(this.getCharacter(this.getCurrentRoom().getCharactersID().get(i)));
        }
        return currentCharacters;
    }

    /**
     * Finds all the items in the currentRoom and puts their names into an ArrayList
     * @return an ArrayList of all the item names in the currentRoom
     */
    public ArrayList<String> getCurrentRoomItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getItemsID().size(); i++) {
            itemNames.add(this.getItem(this.getCurrentRoom().getItemsID().get(i)).getName());
        }
        return itemNames;
    }

    /**
     * Finds all the characters in the currentRoom and puts their names into an ArrayList
     * @return an ArrayList of all the character names in the currentRoom
     */
    public ArrayList<String> getCurrentRoomCharacterNames() {
        ArrayList<String> characterNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getCharactersID().size(); i++) {
            if (!this.getCurrentRoom().getCharactersID().get(i).equals("character_martinStaryAgentPlayer")) {
                characterNames.add(this.getCharacter(this.getCurrentRoom().getCharactersID().get(i)).getName());
            }
        }
        return characterNames;
    }

    /**
     * Finds out if the chosen room has a character that has the mandatoryTalk parameter set to true, this doesn't count the player
     * @param room the room to check
     * @return -true if the rooms has a character with mandatoryTalk == True <br>-false if the room doesn't have a character with mandatoryTalk == True
     */
    public boolean hasMandatoryTalk(Room room) {
        boolean hasMandatoryTalk = false;
        for (int i = 0; i < room.getCharactersID().size() - 1; i++) {
            if (!room.getCharactersID().get(i).equals("character_martinStaryAgentPlayer") && this.getCharacter(room.getCharactersID().get(i)).isMandatoryTalk()) {
                hasMandatoryTalk = true;
            }
        }
        return hasMandatoryTalk;
    }

    /**
     * Finds all the items in the currentRoom with state 2 which mean they have been found by the player
     * @return an ArrayList of all the items with state == 2 in the current rooms
     */
    public ArrayList<Item> getCurrentRoomFoundItems() {
        ArrayList<Item> currentFoundItems = this.getCurrentRoomItems();
        for (int i = 0; i < currentFoundItems.size(); i++) {
            if (currentFoundItems.get(i).getState() != 2) {
                currentFoundItems.remove((int)i);
            }
        }
        return currentFoundItems;
    }

    /**
     * Finds the names of all the items in the currentRoom with state 2 which mean they have been found by the player
     * @return an ArrayList of all the names of items with state == 2 in the current rooms
     */
    public ArrayList<String> getCurrentRoomFoundItemNames() {
        ArrayList<String> currentFoundItemNames = new ArrayList<>();
        for (int i = 0; i < getCurrentRoomFoundItems().size(); i++) {
            currentFoundItemNames.add(getCurrentRoomFoundItems().get(i).getName());
        }
        return currentFoundItemNames;
    }

    /**
     * Returns a print-ready version of the found items in the currentRoom ArrayList
     * @return a print-ready version of the found items in the currentRoom ArrayList
     */
    public String currentFoundItemsToString() {
        if (!getCurrentRoomFoundItemNames().isEmpty()) {
            return "Předměty v místnosti: " + getCurrentRoomFoundItemNames() + "\n";
        } else {
            return "";
        }
    }

    /**
     * Finds the room with the corresponding name, the world must contain this room in order to be able to reach it
     * @param roomName the name of the room in a String form, the name doesn't have to be normalized because the method does it
     * @return the room that has this name, ensure that every room has a unique name
     */
    public Room getRoomByCompatibleName(String roomName) {
        for (Room room : this.rooms) {
            String compatibleName = Normalizer.normalize(room.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(roomName)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Finds the character with the corresponding name, the world must contain this character in order to be able to reach it
     * @param characterName the name of the character in a String form, the name doesn't have to be normalized because the method does it
     * @return the character that has this name, ensure that every character has a unique name
     */
    public Character getCharacterByCompatibleName(String characterName) {
        for (Character character : this.characters) {
            String compatibleName = Normalizer.normalize(character.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(characterName)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Finds the item with the corresponding name, the world must contain this item in order to be able to reach it
     * @param itemName the name of the item in a String form, the name doesn't have to be normalized because the method does it
     * @return the item that has this name, ensure that every item has a unique name
     */
    public Item getItemByCompatibleName(String itemName) {
        for (Item item : this.items) {
            String compatibleName = Normalizer.normalize(item.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if a room with this name exists, the name doesn't have to be normalized because method does it
     * @param name the name of the room in String form, doesn't have to be normalized
     * @return -true if the room exists <br>-false if the room doesn't exist
     */
    public boolean roomWithCompatibleNameExists(String name) {
        for (Room room : this.rooms) {
            String compatibleName = Normalizer.normalize(room.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an item with this name exists, the name doesn't have to be normalized because method does it
     * @param name the name of the item in String form, doesn't have to be normalized
     * @return -true if the item exists <br>-false if the item doesn't exist
     */
    public boolean itemWithCompatibleNameExists(String name) {
        for (Item item : this.items) {
            String compatibleName = Normalizer.normalize(item.getName(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            if (compatibleName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Find the normalized names of all the adjacent rooms to the chosen room
     * @param room the chosen room to check the adjacent rooms
     * @return an ArrayList of the normalized names of the rooms adjacent to the chosen room
     */
    public ArrayList<String> adjacentRoomCompatibleNames(Room room) {
        ArrayList<String> adjacentRoomNames = new ArrayList<>();
        for (int i = 0; i < room.getAdjacentRoomsID().size(); i++) {
            String adjacentRoomName = Normalizer.normalize(room.getAdjacentRoomsID().get(i), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            adjacentRoomNames.add(adjacentRoomName);
        }
        return adjacentRoomNames;
    }

    /**
     * Find a room containing a character with the selected characterID
     * @param characterID ID of the character that the method will be checking for
     * @return the room with the chosen character, null if it doesn't exist
     */
    public Room findRoomWithCharacter(String characterID) {
        for (Room room : this.rooms) {
            if (room.getCharactersID().contains(characterID)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Moves the chosen character from its current room to the chosen room
     * @param characterID the ID of the chosen character
     * @param roomID the ID of the chosen room to move to
     */
    public void moveCharacter(String characterID, String roomID) {
        if (this.getCharacters().contains(getCharacter(characterID)) && this.getRooms().contains(getRoom(roomID))) {
            Character character = getCharacter(characterID);
            Room room = getRoom(roomID);
            findRoomWithCharacter(characterID).getCharactersID().remove(characterID);
            room.getCharactersID().add(characterID);
        }
    }

    /**
     * Find all the adjacent rooms and returns their names
     * @return an ArrayList of the names of all the adjacent rooms
     */
    public ArrayList<String> getCurrentAdjacentRooms() {
        ArrayList<String> adjacentRoomNames = new ArrayList<>();
        for (int i = 0; i < this.getCurrentRoom().getAdjacentRoomsID().size(); i++) {
            adjacentRoomNames.add(this.getRoom(this.getCurrentRoom().getAdjacentRoomsID().get(i)).getName());
        }
        return adjacentRoomNames;
    }

    /**
     * Returns the IDs of all the rooms the world contains
     * @return an ArrayList of all the room IDs of all the rooms that the world contains
     */
    public ArrayList<String> getRoomsID() {
        ArrayList<String> roomsID = new ArrayList<>();
        for (Room room : this.rooms) {
            roomsID.add(room.getId());
        }
        return roomsID;
    }

    //unused
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
