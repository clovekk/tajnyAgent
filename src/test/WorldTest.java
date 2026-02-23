package test;

import game.*;
import game.Character;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    @Test
    void getCurrentRoom() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Room result = world.getCurrentRoom();

        Assertions.assertEquals("room_tunnel", result.getId());
    }

    @Test
    void getItem() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Item result = world.getItem("item_rudaBook");

        Assertions.assertEquals("item_rudaBook", result.getId());
    }

    @Test
    void getRoom() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Room result = world.getRoom("room_hall");

        Assertions.assertEquals("room_hall", result.getId());
    }

    @Test
    void getCharacter() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Character result = world.getCharacter("character_tomasGuard");

        Assertions.assertEquals("character_tomasGuard", result.getId());
    }

    @Test
    void hasMandatoryTalk() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        boolean result = world.hasMandatoryTalk(world.getRoom("room_tunnel"));

        Assertions.assertEquals(true, result);
    }

    @Test
    void getRoomByCompatibleName() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Room result = world.getRoomByCompatibleName("chodba");

        Assertions.assertEquals("room_hall", result.getId());
    }

    @Test
    void getCharacterByCompatibleName() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Character result = world.getCharacterByCompatibleName("hlidac tomas");

        Assertions.assertEquals("character_tomasGuard", result.getId());
    }

    @Test
    void getItemByCompatibleName() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Item result = world.getItemByCompatibleName("kniha");

        Assertions.assertEquals("item_rudaBook", result.getId());
    }

    @Test
    void findRoomWithCharacter() {
        WorldLoader wl = new WorldLoader();
        World world = wl.loadNewWorld("/gamedata.json");
        Room result = world.findRoomWithCharacter("character_pepaCook");

        Assertions.assertEquals("room_kitchen", result.getId());
    }
}