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
    public String execute() {
        if (world.getPlayer().hasItem(world.getItemByCompatibleName(itemName))) {
            if (world.getCurrentRoom().getId().equals("room_checkpoint") && world.getItemByCompatibleName(itemName).getId().equals("item_pistolGun")) {
                System.out.println("Zastřelil jsi Rudu a nyní je vchod k veliteli nechráněn");
                return "7";
            }
            if (world.getItemByCompatibleName(itemName).getState() != 3) { //state 3 = used
                world.getItemByCompatibleName(itemName).setState(3);
                System.out.println(world.getItemByCompatibleName(itemName).getUseText());
            } else {
                System.out.println("Tento předmět jsi již použil");
            }
        } else {
            System.out.println("Tento předmět nemáš");
        }
        return "";
    }
}
