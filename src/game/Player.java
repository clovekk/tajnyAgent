package game;

import java.util.ArrayList;

public class Player extends Character {
    private ArrayList<String> inventoryID;
    private int suspiciousness;

    public Player(String id, String name, ArrayList<String> dialogues, boolean mandatoryTalk, ArrayList<String> inventoryID, int suspiciousness) {
        super(id, name, dialogues, mandatoryTalk);
        this.inventoryID = inventoryID;
        this.suspiciousness = suspiciousness;
    }

    public Player() {
        this.inventoryID = new ArrayList<>();
        this.suspiciousness = 0;
    }

    public ArrayList<String> getInventoryID() {
        return inventoryID;
    }
    public int getSuspiciousness() {
        return suspiciousness;
    }

    public void setInventoryID(ArrayList<String> inventoryID) {
        this.inventoryID = inventoryID;
    }
    public void setSuspiciousness(int suspiciousness) {
        this.suspiciousness = suspiciousness;
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
