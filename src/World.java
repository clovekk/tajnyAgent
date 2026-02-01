import Commands.*;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private Room currentRoom;
    private ArrayList<Room> rooms;
    private int time;
    private Player player;
    private ArrayList<String> hints;
    private HashMap<String, Command> commands;
    private boolean end;

    public Command readCommand(String command) {
        return null;
    }
}
