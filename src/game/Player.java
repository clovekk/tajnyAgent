package game;

import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<String> inventoryID;

    public Player(String id, String name, ArrayList<String> dialogues, boolean mandatoryTalk, ArrayList<String> inventoryID) {
        super(id, name, dialogues, mandatoryTalk);
        this.inventoryID = inventoryID;
    }

    public Player() {
        this.inventoryID = new ArrayList<>();
    }

    public ArrayList<String> getInventoryID() {
        return inventoryID;
    }
    public void setInventoryID(ArrayList<String> inventoryID) {
        this.inventoryID = inventoryID;
    }

    public boolean hasItem(Item item) {
        return this.hasItem(item.getId());
    }

    public boolean hasItem(String itemID) {
        for (String currentItemID : inventoryID) {
            if (currentItemID.equals(itemID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "inventoryID=" + inventoryID +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dialogues=" + dialogues +
                ", mandatoryTalk=" + mandatoryTalk +
                '}';
    }
}
