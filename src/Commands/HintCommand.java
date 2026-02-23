package Commands;

import game.*;

//napoveda

/**
 * this command shows the hint
 * @author Adam Dluhoš
 */
public class HintCommand implements Command {
    private World world;

    public HintCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        System.out.println(world.getHints().get(world.getGameState()));
        return "";
    }
}
