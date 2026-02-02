package Commands;

import game.*;

//konec
public class EndCommand implements Command {
    private World world;

    public EndCommand(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        world.setEnd(true);
        //TODO dodelat ukladani hry pred ukoncenim programu
    }
}
