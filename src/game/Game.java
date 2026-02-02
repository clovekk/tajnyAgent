package game;

import Commands.Command;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private World world;
    private HashMap<String, Command> commands;

    public Game(World world, HashMap<String, Command> commands) {
        this.world = world;
        this.commands = commands;
    }

    public Game() {
        this.world = new World();
        this.commands = new HashMap<>();
    }

    public World getWorld() {
        return world;
    }
    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Asks the user if they want to load a saved game or if they want to load a new save.
     * This method is best suited to be used in a while loop that repeats until the input is valid
     * (that is achieved by putting this method into a condition of a while loop that has an empty body).
     * @return -true if one of the valid load options has been chosen <br>-false if the input is invalid
     */
    public boolean worldSelection() {
        Scanner scn = new Scanner(System.in);
        String consoleInput = "";
        System.out.print("Chcete načíst uložený svět v souboru(C), nebo začít novou hru(N)?\n>>>");
        consoleInput = scn.nextLine();

        switch (consoleInput.toUpperCase()) {
            case "C":
                //TODO write a method that loads a saved game
                return true;

            case "N":
                WorldLoader wl = new WorldLoader();
                world = wl.loadNewWorld("/gamedata.json");
                return true;

            default:
                System.out.println("Neplatný vstup");
                return false;
        }
    }

    public void createCommands() {
        //TODO put all commands with their keys into the hashmap
    }

    public void run() {
        while(!worldSelection()) {
            //while statement repeats until the user types one of the valid inputs (C/c/N/n),
            //because the condition is a negation of a boolean method, so the condition will only ever
            //not be met if the method returns true, which it only does after a valid input,
            //that is the reason whz the statement has empty body
        }

        //temporary test to see if world loaded properly
        System.out.println(world);

        //TODO complete game loop
    }
}
