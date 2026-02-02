package game;

public class WorldLoader {
    public World loadNewWorld(String resourcePath) {
        World world = new World();
        GameData data = GameData.loadGameDataFromResources(resourcePath);

        /*System.out.println("Items: " + data.items);
        System.out.println("Characters: " + data.characters);
        System.out.println("Rooms: " + data.rooms);
        System.out.println("Future tasks: " + data.futureTasks);
        System.out.println("Player: " + data.player);
        System.out.println("Hints:" + data.hints);
        System.out.println(data);*/

        world.setRooms(data.rooms);
        world.setFutureTasks(data.futureTasks);
        world.setItems(data.items);
        world.getItems().addAll(data.mapItems);
        world.setCharacters(data.characters);
        world.setCurrentRoom(data.rooms.getFirst());
        world.setPlayer(data.player);
        world.setHints(data.hints);

        return world;
    }
}
