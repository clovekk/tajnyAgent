package game;

import Commands.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private World world;
    private ArrayList<String> commands;

    public Game(World world, ArrayList<String> commands) {
        this.world = world;
        this.commands = commands;
    }

    public Game() {
        this.world = new World();
        this.commands = new ArrayList<>();
    }

    public World getWorld() {
        return world;
    }
    public ArrayList<String> getCommands() {
        return commands;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public void setCommands(ArrayList<String> commands) {
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
        commands.add("konec");
        commands.add("prikazy");
        commands.add("napoveda");
        commands.add("zakresli");
        commands.add("jdi");
        commands.add("seber");
        commands.add("proheldej");
        commands.add("spi");
        commands.add("mluv");
        commands.add("ukoly");
        commands.add("zahod");
        commands.add("pouzij");
        commands.add("cekej");
    }

    public ArrayList<String> getPlayerCommand() {
        Scanner scn = new Scanner(System.in);
        String consoleInput = "";
        System.out.print("\nZadej příkaz >>>");
        consoleInput = scn.nextLine().replaceAll("\\s+", " ").replaceAll("-+", "-").toLowerCase();
        System.out.println("consoleinput: " + consoleInput); //temporary for testing

        String[] fullCommand = consoleInput.split("-");
        String firstCommand = fullCommand[0];
        //accent marks are firstly separated from the text, then they are deleted by replacing all non-ASCII characters with nothing
        firstCommand = Normalizer.normalize(firstCommand, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

        ArrayList<String> splitCommands = new ArrayList<>();
        splitCommands.add(firstCommand);

        String commandArgument = "";
        if (fullCommand.length > 1) {
            commandArgument = fullCommand[1];
            //accent marks are firstly separated from the text, then they are deleted by replacing all non-ASCII characters with nothing
            commandArgument = Normalizer.normalize(commandArgument, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
            splitCommands.add(commandArgument);
        }
        System.out.println(splitCommands);
        return splitCommands;
    }

    public void run() {
        createCommands();
        while(!worldSelection()) {
            //while statement repeats until the user types one of the valid inputs (C/c/N/n),
            //because the condition is a negation of a boolean method, so the condition will only ever
            //not be met if the method returns true, which it only does after a valid input,
            //that is the reason why the statement has empty body
        }

        System.out.println(world); //temporary test to see if world loaded properly
        world.getCharacter("character_tunnelGuard").setMandatoryTalk(false); //temp

        while (!this.world.isEnd()) {
            System.out.println("\nAktuální místnost: " + world.getCurrentRoom().getName() + "\n" + world.getCurrentRoom().getDescription());
            System.out.println("Aktuální čas:" + world.getTime() % 24 + "(Celkem uběhlo hodin: " + world.getTime() + ")");
            System.out.println("Postavy v místnosti: " + world.getCurrentRoomCharacterNames());
            System.out.print(world.currentFoundItemsToString());

            ArrayList<String> splitCommands = getPlayerCommand();
            String firstCommand = splitCommands.getFirst();
            String commandArgument = "";
            if (splitCommands.size() > 1) {
                commandArgument = splitCommands.get(1);
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

                case "seber":
                    Command pickUpCommand = new PickUpCommand(this.world, commandArgument);
                    pickUpCommand.execute();
                    break;

                case "prohledej":
                    Command searchCommand = new SearchCommand(this.world);
                    searchCommand.execute();
                    break;

                case "spi":
                    Command sleepCommand = new SleepCommand(this.world);
                    sleepCommand.execute();
                    break;

                case "mluv":
                    Command talkCommand = new TalkCommand(this.world, commandArgument);
                    talkCommand.execute();
                    break;

                case "ukoly":
                    Command taskListCommand = new TaskListCommand(this.world);
                    taskListCommand.execute();
                    break;

                case "zahod":
                    Command throwAwayCommand = new ThrowAwayCommand(this.world, commandArgument);
                    throwAwayCommand.execute();
                    break;

                case "pouzij":
                    Command useCommand = new UseCommand(this.world, commandArgument);
                    useCommand.execute();
                    break;

                case "cekej":
                    Command waitCommand = new WaitCommand(this.world);
                    waitCommand.execute();
                    break;

                default:
                    System.out.println("Neplatný příkaz");
                    break;
            }
        }
        //TODO complete game loop
    }
}
