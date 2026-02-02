package game;

import java.util.ArrayList;

public class MapItem extends Item{
    private ArrayList<String> mappedRoomsID;

    public MapItem(ArrayList<String> mappedRoomsID) {
        this.mappedRoomsID = mappedRoomsID;
    }

    public MapItem() {
        this.mappedRoomsID = new ArrayList<>();
    }

    public ArrayList<String> getMappedRoomsID() {
        return mappedRoomsID;
    }
    public void setMappedRoomsID(ArrayList<String> mappedRoomsID) {
        this.mappedRoomsID = mappedRoomsID;
    }

    @Override
    public String toString() {
        return "MapItem{" +
                "mappedRoomsID=" + mappedRoomsID +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deletable=" + deletable +
                ", state=" + state +
                ", useText='" + useText + '\'' +
                '}';
    }
}
