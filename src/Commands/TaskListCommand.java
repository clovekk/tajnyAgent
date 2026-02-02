package Commands;

import game.*;

//ukoly
public class TaskListCommand implements Command {
    private World world;

    public TaskListCommand(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        System.out.println(world.getCurrentTasks());
    }
}
