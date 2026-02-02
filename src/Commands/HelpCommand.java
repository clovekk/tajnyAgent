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
    public void execute() {
        System.out.println(commands);
        //TODO udelat vic prehledny vypis prikazu i s jejich popisem
    }
}
