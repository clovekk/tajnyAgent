package Commands;

import game.*;

//spi
public class SleepCommand implements Command {
    private World world;

    public SleepCommand(World world) {
        this.world = world;
    }

    @Override
    public void execute() {
        if (world.getCurrentRoom().getId().equalsIgnoreCase("room_beds")) {
            int baseTime = world.getTime() % 24;
            int sleepTime = 4 + 24 - baseTime;
            world.setTime(world.getTime() + sleepTime);
            System.out.println("Úspěšně jsi se vyspal");
        } else {
            System.out.println("Nejsi v místnosti kde by byla volná postel");
        }
    }
}
