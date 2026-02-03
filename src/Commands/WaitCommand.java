package Commands;

import game.*;

//cekej
public class WaitCommand implements Command {
    private World world;

    public WaitCommand(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        world.setTime(world.getTime() + 6);
    }
}
