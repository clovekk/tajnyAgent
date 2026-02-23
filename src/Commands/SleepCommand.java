package Commands;

import game.*;

//spi

/**
 * this command skips time by sleeping
 * @author Adam Dluhoš
 */
public class SleepCommand implements Command {
    private World world;

    public SleepCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        if (world.getCurrentRoom().getId().equalsIgnoreCase("room_beds")) {
            int baseTime = world.getTime() % 24;
            int sleepTime = 0;
            if (baseTime > 22) {
                sleepTime = 52 - baseTime;
            } else {
                sleepTime = 28 - baseTime;
            }
            world.setTime(world.getTime() + sleepTime);
            System.out.println("Úspěšně jsi se vyspal");
        } else {
            System.out.println("Nejsi v místnosti kde by byla volná postel");
        }
        return "8";
    }
}
