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
    public String execute() {
        if (world.getPlayer().hasItem(world.getItemByCompatibleName(itemName))) {
            if (world.getItemByCompatibleName(itemName).getId().equals("item_baseMap") && world.getItem("item_baseMap").getState() == 4) {
                System.out.println("Vyhodil jsi mapu do popelnice, snad ji tajná služba najde");
                world.getPlayer().getInventoryID().remove("item_baseMap");
                world.getItem("item_trashBag").setDeletable(true);
                return "5";
            }
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
        return "";
    }
}
