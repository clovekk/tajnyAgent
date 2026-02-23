package game;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This class manages the loading and saving of worlds
 * @author Adam Dluhoš
 */
public class WorldLoader {
    public WorldLoader() {
    }

    /**
     * Loads a new world from the specified file in resources
     * @param resourcePath path to the resource file
     * @return an instance of the World class with all the data loaded
     */
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

    /**
     * Loads a saved world from the save file
     * @param resourcePath path to the save file
     * @return an instance of the World class with all the data loaded
     */
    public World loadSavedWorld(String resourcePath) {
        World world = new World();
        SavedGameData data = SavedGameData.loadGameData(Path.of(resourcePath));

        world.setCurrentRoom(data.currentRoom);
        world.setCurrentTasks(data.currentTasks);
        world.setFutureTasks(data.futureTasks);
        world.setItems(data.items);
        world.setCharacters(data.characters);
        world.setRooms(data.rooms);
        world.setTime(data.time);
        world.setStartTime(data.startTime);
        world.setGameState(data.gameState);
        world.setPlayer(data.player);
        world.setHints(data.hints);
        world.setEnd(data.end);
        world.getItems().addAll(data.mapItems);

        return world;
    }

    /**
     * Saves the data of the world into the save file
     * @param resourcePath path to the save file
     * @param world the world to be saved
     */
    public void saveLoadedWorld(String resourcePath, World world) {
        SavedGameData data = new SavedGameData();

        for (int i = 0; i < world.getItems().size(); i++) {
            if (!world.getItems().get(i).getId().equals("item_baseMap")) {
                data.items.add(world.getItems().get(i));
            } else {
                data.mapItems.add((MapItem) world.getItems().get(i));
            }
        }

        data.currentRoom = world.getCurrentRoom();
        data.currentTasks = world.getCurrentTasks();
        data.futureTasks = world.getFutureTasks();
        data.characters = world.getCharacters();
        data.rooms = world.getRooms();
        data.time = world.getTime();
        data.startTime = world.getStartTime();
        data.gameState = world.getGameState();
        data.player = world.getPlayer();
        data.hints = world.getHints();
        data.end = false;

        SavedGameData.saveGameData(Path.of(resourcePath), data);
    }
}
