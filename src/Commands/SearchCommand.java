package Commands;

import game.*;

//proheldej
public class SearchCommand implements Command {
    private World world;

    public SearchCommand(World world) {
        this.world = world;
    }

    @Override
    public String execute() {
        if (!world.getCurrentRoom().getItemsID().isEmpty()) {
            for (int i = 0; i < world.getCurrentRoomItems().size(); i++) {
                world.getCurrentRoomItems().get(i).setState(2); //state 2 == item has been discovered
            }
            System.out.println("Nalezené předměty: " + world.getCurrentRoomItemNames());
        }  else {
            System.out.println("Nenalezl jsi žádné předměty");
        }
        world.setTime(world.getTime() + 1);
        return "";
    }
}
