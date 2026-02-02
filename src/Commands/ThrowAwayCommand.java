package Commands;

import game.*;

//zahod
public class ThrowAwayCommand implements Command {
    private World world;
    private String itemName;

    public ThrowAwayCommand(World world, String itemName) {
        this.world = world;
        this.itemName = itemName;
    }

    @Override
    public void execute() {
        if (world.getPlayer().hasItem(world.getItemByCompatibleName(itemName))) {
            if (world.getItemByCompatibleName(itemName).isDeletable()) {
                world.getPlayer().getInventoryID().remove(world.getItemByCompatibleName(itemName).getId());
                world.getItems().remove(world.getItemByCompatibleName(itemName));
                System.out.println("Zahodil jsi předmět");
            } else {
                System.out.println("Tento předmět nemůžeš zahodit");
            }
        } else {
            System.out.println("Tento předmět neexistuje");
        }
    }
}
