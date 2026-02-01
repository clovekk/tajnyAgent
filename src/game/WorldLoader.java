package game;

public class WorldLoader {
    public World loadNewWorld(String resourcePath) {
        World world = new World();
        GameData data = GameData.loadGameDataFromResources(resourcePath);

        System.out.println("Items: " + data.items.size());
        System.out.println("Characters: " + data.characters.size());
        System.out.println("Rooms: " + data.rooms.size());
        System.out.println("Future tasks: " + data.futureTasks.size());
        System.out.println(data);

        world.setRooms(data.rooms);
        world.setFutureTasks(data.futureTasks);
        world.setItems(data.items);
        world.setCharacters(data.characters);
        world.setCurrentRoom(data.rooms.getFirst());
        world.setPlayer(data.player);
        world.setHints(data.hints);

        return world;
    }
}
