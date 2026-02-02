package Commands;

import game.*;

//pouzij
public class UseCommand implements Command {
    private World world;
    private String itemName;

    public UseCommand(World world, String itemName) {
        this.world = world;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        if (world.getPlayer().hasItem(world.getItemByCompatibleName(itemName))) {
            if (world.getItemByCompatibleName(itemName).getState() != 3) { //state 3 = used
                world.getItemByCompatibleName(itemName).setState(3);
                System.out.println(world.getItemByCompatibleName(itemName).getUseText());
            } else {
                System.out.println("Tento předmět jsi již použil");
            }
        } else {
            System.out.println("Tento předmět nemáš");
        }
    }
}
