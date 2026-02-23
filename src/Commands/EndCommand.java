package Commands;

import game.*;

//konec

/**
 * This commands ends the game
 * @author Adam Dluhoš
 */
public class EndCommand implements Command {
    private World world;

    public EndCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        WorldLoader wl = new WorldLoader();
        wl.saveLoadedWorld("saves/savedGame.json", this.world);
        this.world.setEnd(true);
        return "";
        //TODO dodelat ukladani hry pred ukoncenim programu
    }
}
