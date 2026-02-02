package Commands;

import java.util.HashMap;

//prikazy
public class HelpCommand implements Command {
    private HashMap<String, Command> commands;

    public HelpCommand(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println(commands.keySet());
        //TODO udelat vic prehledny vypis prikazu i s jejich popisem
    }
}
