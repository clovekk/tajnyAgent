package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This class serves as teh container for world data when loading or saving a world
 * @author Adam Dluhoš
 */
public class SavedGameData {
    public Room currentRoom;
    public ArrayList<String> currentTasks;
    public ArrayList<String> futureTasks;
    public ArrayList<Item> items;
    public ArrayList<Character> characters;
    public ArrayList<Room> rooms;
    public int time;
    public int startTime;
    public int gameState;
    public Player player;
    public ArrayList<String> hints;
    public boolean end;
    public ArrayList<MapItem> mapItems;

    public SavedGameData() {
        this.currentRoom = new Room();
        this.currentTasks = new ArrayList<>();
        this.futureTasks = new ArrayList<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.time = 0;
        this.startTime = 0;
        this.gameState = 0;
        this.player = new Player();
        this.hints = new ArrayList<>();
        this.end = false;
        this.mapItems = new ArrayList<>();
    }

    /**
     * Loads the game data in the save file if compatible
     * @param filePath path to the save file
     * @return all the data loaded from the save file
     */
    public static SavedGameData loadGameData(Path filePath) throws IOException {
        Gson gson = new Gson();

        try (Reader r = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            return gson.fromJson(r, SavedGameData.class);
        } catch (IOException e) {
            throw new IOException("Načítaný soubor neexistuje");
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    /**
     * SAves the current world status to a save file
     * @param filePath path to the save file
     * @param savedGameData the data to be saved
     */
    public static void saveGameData(Path filePath, SavedGameData savedGameData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer w = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            gson.toJson(savedGameData, w);
        } catch (Exception e) {
            throw new RuntimeException("Chyba při ukládání JSON do souboru: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "SavedGameData{" +
                "currentRoom=" + currentRoom +
                ", currentTasks=" + currentTasks +
                ", futureTasks=" + futureTasks +
                ", items=" + items +
                ", characters=" + characters +
                ", rooms=" + rooms +
                ", time=" + time +
                ", startTime=" + startTime +
                ", gameState=" + gameState +
                ", player=" + player +
                ", hints=" + hints +
                ", end=" + end +
                '}';
    }
}
