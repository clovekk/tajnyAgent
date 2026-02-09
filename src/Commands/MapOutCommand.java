package Commands;

import game.*;

//zakresli
public class MapOutCommand implements Command {
    private World world;

    public MapOutCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        if (world.getPlayer().hasItem("item_baseMap")) {
            MapItem baseMap = (MapItem) world.getItem("item_baseMap");
            if (!baseMap.getMappedRoomsID().contains(world.getCurrentRoom().getId())) {
                baseMap.getMappedRoomsID().add(world.getCurrentRoom().getId());
            } else {
                System.out.println("Tuto místnost jsi již do mapy zakreslil");
            }
        } else {
            System.out.println("Nemáš u sebe mapu");
        }
        world.setTime(world.getTime() + 2);
        return "";
    }
}
