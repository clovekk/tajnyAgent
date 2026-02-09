package Commands;

import game.*;

//konec
public class EndCommand implements Command {
    private World world;

    public EndCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        world.setEnd(true);
        return "";
        //TODO dodelat ukladani hry pred ukoncenim programu
    }
}
