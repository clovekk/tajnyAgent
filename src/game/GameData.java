package game;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class GameData {
    public ArrayList<Room> rooms;
    public Player player;
    public ArrayList<Character> characters;
    public ArrayList<Item> items;
    public ArrayList<String> futureTasks;
    public ArrayList<String> hints;

    public static GameData loadGameDataFromResources(String resourcePath) {
        Gson gson = new Gson();

        try (InputStream is = GameData.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalStateException("Nenalezen resource: " + resourcePath +
                        " (zkontrolujte, že soubor je v src/main/resources).");
            }
            return gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), GameData.class);
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "GameData{" +
                "rooms=" + rooms +
                ", player=" + player +
                ", characters=" + characters +
                ", items=" + items +
                ", futureTasks=" + futureTasks +
                ", hints=" + hints +
                '}';
    }
}
