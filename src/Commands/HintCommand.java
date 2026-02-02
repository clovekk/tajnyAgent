package Commands;

import game.*;

//napoveda
public class HintCommand implements Command {
    private World world;

    public HintCommand(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        System.out.println(world.getHints().get(world.getGameState()));
    }
}
