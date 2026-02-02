package game;

import java.lang.Character;

public class Item {
    protected String id;
    protected String name;
    protected boolean deletable;
    protected int state;

    public Item(String id, String name, boolean deletable, int state) {
        this.id = id;
        this.name = name;
        this.deletable = deletable;
        this.state = state;
    }

    public Item() {
        this.id = "unspecified";
        this.name = "unspecified";
        this.deletable = false;
        this.state = 0;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isDeletable() {
        return deletable;
    }
    public int getState() {
        return state;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }
    public void setState(int state) {
        this.state = state;
    }

    public String getItemType(){
        try {
            StringBuilder builderItemType = new StringBuilder();
            int i = this.id.length() - 1;
            while (!Character.isUpperCase(this.id.charAt(i)) && Character.isLowerCase(this.id.charAt(i - 1))) {
                builderItemType.insert(0, this.id.charAt(i));
                i--;
                System.out.println("cycle");
            }
            return builderItemType.toString();
        } catch (Exception e) {
            throw new RuntimeException("Incorrect item ID");
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", deletable=" + deletable +
                ", state=" + state +
                '}';
    }
}
