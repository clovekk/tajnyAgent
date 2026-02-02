package game;

import Commands.*;

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
        commands.put("konec", new EndCommand(this.world));
        commands.put("prikazy", new HelpCommand(commands));
        commands.put("napoveda", new HintCommand(this.world));
        commands.put("zakresli", new MapOutCommand(this.world));
        //TODO put all commands with their keys into the hashmap !!! dont put commands into hashmap but into the switch, delete the hashmap asap
    }

    public void run() {
        createCommands();
        Scanner scn = new Scanner(System.in);
        String consoleInput = "";
        while(!worldSelection()) {
            //while statement repeats until the user types one of the valid inputs (C/c/N/n),
            //because the condition is a negation of a boolean method, so the condition will only ever
            //not be met if the method returns true, which it only does after a valid input,
            //that is the reason whz the statement has empty body
        }

        //temporary test to see if world loaded properly
        System.out.println(world);

        while (!this.world.isEnd()) {
            System.out.println("\nAktuální místnost: " + world.getCurrentRoom().getName() + "\n" + world.getCurrentRoom().getDescription());
            System.out.print("\nZadej příkaz >>>");
            consoleInput = scn.nextLine();
            String[] fullCommand = consoleInput.split("-");
            String firstCommand = fullCommand[0];
            String commandArgument = "";
            if (fullCommand.length > 1) {
                commandArgument = fullCommand[1];
            }

            System.out.println(firstCommand + "\n" + commandArgument);

            switch (firstCommand) {
                case "konec":
                    Command endCommand = new EndCommand(this.world);
                    endCommand.execute();
                    //temporary test to see world data after ending
                    System.out.println(world);
                    break;

                case "prikazy":
                    Command helpCommand = new HelpCommand(commands);
                    helpCommand.execute();
                    break;

                case "napoveda":
                    Command hintCommand = new HintCommand(this.world);
                    hintCommand.execute();
                    break;

                case "zakresli":
                    Command mapOutCommand = new MapOutCommand(this.world);
                    mapOutCommand.execute();
                    break;

                case "jdi":
                    Command moveCommand = new MoveCommand(this.world, commandArgument);
                    moveCommand.execute();
                    break;

                default:
                    System.out.println("Neplatný příkaz");
                    break;
            }
        }
        //TODO complete game loop
    }
}
