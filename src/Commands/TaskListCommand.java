package Commands;

import game.*;

//ukoly

/**
 * this command shows all teh tasks
 * @author Adam Dluhoš
 */
public class TaskListCommand implements Command {
    private World world;

    public TaskListCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        System.out.println(world.getCurrentTasks());
        return "";
    }
}
