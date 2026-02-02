package game;

import java.util.ArrayList;

public class Character {
    protected String id;
    protected String name;
    protected ArrayList<String> dialogues;
    protected boolean mandatoryTalk;

    public Character(String id, String name, ArrayList<String> dialogues, boolean mandatoryTalk) {
        this.id = id;
        this.name = name;
        this.dialogues = dialogues;
        this.mandatoryTalk = mandatoryTalk;
    }

    public Character() {
        this.id = "unspecified";
        this.name = "unspecified";
        this.dialogues = new ArrayList<>();
        this.mandatoryTalk = false;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ArrayList<String> getDialogues() {
        return dialogues;
    }
    public boolean isMandatoryTalk() {
        return mandatoryTalk;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
    }
    public void setMandatoryTalk(boolean mandatoryTalk) {
        this.mandatoryTalk = mandatoryTalk;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dialogues=" + dialogues +
                ", mandatoryTalk=" + mandatoryTalk +
                '}';
    }
}
