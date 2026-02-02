package Commands;

import game.*;

public class MoveCommand implements Command {
    private World world;
    private String newRoomName;

    public MoveCommand(World world, String roomName) {
        this.world = world;
        this.newRoomName = roomName;
    }

    @Override
    public void execute() {
        if (!world.getRooms().contains(world.getRoomByName(newRoomName))) {
            System.out.println("Neplatný název místnosti");
        } else {
            Room newRoom = world.getRoomByName(newRoomName);
            if (world.getCurrentRoom().getAdjacentRoomsID().contains(newRoom.getId())) {
                if (!world.getCurrentRoom().getCharactersID().isEmpty() && world.getCharacter(world.getCurrentRoom().getCharactersID().getFirst()).isMandatoryTalk()) {
                    System.out.println("Vypadá to že by si s tebou chtěl " + world.getCharacter(world.getCurrentRoom().getCharactersID().getFirst()).getName() + " promluvit než tě nechá odejít");
                } else {
                    world.setCurrentRoom(newRoom);
                }
            } else {
                System.out.println("Místnost " + newRoomName + " není vedle místnosti " + world.getCurrentRoom() + ", kde se právě nacházíš");
            }
        }
    }
}
