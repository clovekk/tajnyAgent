package game;

public class Item {
    private String id;
    private String name;
    private boolean deletable;
    private int state;

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
