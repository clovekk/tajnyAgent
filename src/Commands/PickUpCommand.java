package Commands;

import game.*;

//seber
public class PickUpCommand implements Command {
    private World world;
    private String itemName;

    public PickUpCommand(World world, String itemName) {
        this.world = world;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        if (!world.getCurrentRoomItems().isEmpty() && world.getCurrentRoom().getItemsID().contains(world.getItemByName(itemName).getId())) {
            Item item = world.getItemByName(itemName);
            if (item.getState() == 2) {
                if (world.getPlayer().getInventoryID().size() < 3) {
                    world.getPlayer().getInventoryID().add(item.getId());
                    world.getCurrentRoom().getItemsID().remove(item.getId());
                } else {
                    System.out.println("Nemáš u sebe dostatek místa na sebrání předmětu" + itemName);
                }
            } else {
                System.out.println("Předmět " + itemName + " v místnosti nevidíš");
            }
        } else {
            System.out.println("Předmět " + itemName + " v místnosti nevidíš");
        }
    }
}
