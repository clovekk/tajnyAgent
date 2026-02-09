package Commands;

import java.util.ArrayList;
import java.util.HashMap;

//prikazy
public class HelpCommand implements Command {
    private ArrayList<String> commands;

    public HelpCommand(ArrayList<String> commands) {
        this.commands = commands;
    }

    @Override
    public String execute() {
        System.out.println(commands);
        return "";
        //TODO udelat vic prehledny vypis prikazu i s jejich popisem
    }
}
