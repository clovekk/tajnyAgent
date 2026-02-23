package game;

import java.util.ArrayList;

/**
 * This class represents the players game character
 * @author Adam Dluhoš
 */
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

    /**
     * Searches the players inventory for the selected item
     * @param item the item we are looking for
     * @return -true if the item is present in the players inventory <br>-false if the item is not present in the players inventory
     */
    public boolean hasItem(Item item) {
        return this.hasItem(item.getId());
    }

    /**
     * Searches the players inventory for the selected itemID
     * @param itemID the ID of the item we are looking for
     * @return -true if the itemID is present in the players inventory <br>-false if the itemID is not present in the players inventory
     */
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
