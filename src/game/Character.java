package game;

import java.util.ArrayList;

public class Character {
    private String id;
    private String name;
    private ArrayList<String> dialogues;

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dialogues=" + dialogues +
                '}';
    }
}
