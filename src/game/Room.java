package game;

import java.util.ArrayList;

public class Room {
    private String id;
    private String name;
    private String description;
    private ArrayList<String> adjacentRoomsID;
    private ArrayList<String> charactersID;
    private ArrayList<String> itemsID;

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ArrayList<String> getAdjacentRoomsID() {
        return adjacentRoomsID;
    }
    public ArrayList<String> getCharactersID() {
        return charactersID;
    }
    public ArrayList<String> getItemsID() {
        return itemsID;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAdjacentRoomsID(ArrayList<String> adjacentRoomsID) {
        this.adjacentRoomsID = adjacentRoomsID;
    }
    public void setCharactersID(ArrayList<String> charactersID) {
        this.charactersID = charactersID;
    }
    public void setItemsID(ArrayList<String> itemsID) {
        this.itemsID = itemsID;
    }

    public void connectRooms(Room room) {
        this.adjacentRoomsID.add(room.getId());
        room.adjacentRoomsID.add(this.getId());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", adjacentRoomsID=" + adjacentRoomsID +
                ", charactersID=" + charactersID +
                ", itemsID=" + itemsID +
                '}';
    }
}
