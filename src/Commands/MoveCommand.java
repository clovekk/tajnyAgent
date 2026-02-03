package Commands;

import game.*;

//jdi
public class MoveCommand implements Command {
    private World world;
    private String newRoomName;

    public MoveCommand(World world, String newRoomName) {
        this.world = world;
        this.newRoomName = newRoomName;
    }

    @Override
    public void execute() {
        if (!world.roomWithCompatibleNameExists(newRoomName)) {
            System.out.println("Neplatný název místnosti");
        } else {
            Room newRoom = world.getRoomByCompatibleName(newRoomName);
            if (world.getCurrentRoom().getAdjacentRoomsID().contains(newRoom.getId())) {
                if (!world.getCurrentRoom().getCharactersID().isEmpty() && world.hasMandatoryTalk(world.getCurrentRoom())) {
                    System.out.println("Vypadá to že by si s tebou chtěl " + world.getCharacter(world.getCurrentRoom().getCharactersID().getFirst()).getName() + " promluvit než tě nechá odejít");
                } else {
                    if (newRoom.isLocked()) {
                        System.out.println("Do místnosti " + newRoom.getName() + " tě hlídači nepustí");
                    } else {
                        world.getCurrentRoom().getCharactersID().remove(world.getPlayer().getId());
                        newRoom.getCharactersID().add(world.getPlayer().getId());
                        world.setCurrentRoom(newRoom);
                    }
                }
            } else {
                System.out.println("Místnost " + newRoomName + " není vedle místnosti " + world.getCurrentRoom().getName() + ", kde se právě nacházíš");
            }
        }
    }
}
