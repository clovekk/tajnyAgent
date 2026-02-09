package game;

import Commands.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private World world;
    private ArrayList<String> commands;
    private String commandReturn;
    private int startTime;

    public Game(World world, ArrayList<String> commands, String commandReturn, int startTime) {
        this.world = world;
        this.commands = commands;
        this.commandReturn = commandReturn;
        this.startTime = startTime;
    }

    public Game() {
        this.world = new World();
        this.commands = new ArrayList<>();
        this.commandReturn = "";
        this.startTime = 0;
    }

    public World getWorld() {
        return world;
    }
    public ArrayList<String> getCommands() {
        return commands;
    }
    public String getCommandReturn() {
        return commandReturn;
    }
    public int getStartTime() {
        return startTime;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public void setCommands(ArrayList<String> commands) {
        this.commands = commands;
    }
    public void setCommandReturn(String commandReturn) {
        this.commandReturn = commandReturn;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
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

    public void executeCommand(ArrayList<String> splitCommands) {
        String firstCommand = splitCommands.getFirst();
        String commandArgument = "";
        if (splitCommands.size() > 1) {
            commandArgument = splitCommands.get(1);
        }

        System.out.println(firstCommand + "\n" + commandArgument);

        switch (firstCommand) {
            case "konec":
                Command endCommand = new EndCommand(this.world);
                commandReturn = endCommand.execute();
                //temporary test to see world data after ending
                System.out.println(world);
                break;

            case "prikazy":
                Command helpCommand = new HelpCommand(commands);
                commandReturn = helpCommand.execute();
                break;

            case "napoveda":
                Command hintCommand = new HintCommand(this.world);
                commandReturn = hintCommand.execute();
                break;

            case "zakresli":
                Command mapOutCommand = new MapOutCommand(this.world);
                commandReturn = mapOutCommand.execute();
                break;

            case "jdi":
                Command moveCommand = new MoveCommand(this.world, commandArgument);
                commandReturn = moveCommand.execute();
                break;

            case "seber":
                Command pickUpCommand = new PickUpCommand(this.world, commandArgument);
                commandReturn = pickUpCommand.execute();
                break;

            case "prohledej":
                Command searchCommand = new SearchCommand(this.world);
                commandReturn = searchCommand.execute();
                break;

            case "spi":
                Command sleepCommand = new SleepCommand(this.world);
                commandReturn = sleepCommand.execute();
                break;

            case "mluv":
                Command talkCommand = new TalkCommand(this.world, commandArgument);
                commandReturn = talkCommand.execute();
                break;

            case "ukoly":
                Command taskListCommand = new TaskListCommand(this.world);
                commandReturn = taskListCommand.execute();
                break;

            case "zahod":
                Command throwAwayCommand = new ThrowAwayCommand(this.world, commandArgument);
                commandReturn = throwAwayCommand.execute();
                break;

            case "pouzij":
                Command useCommand = new UseCommand(this.world, commandArgument);
                commandReturn = useCommand.execute();
                break;

            case "cekej":
                Command waitCommand = new WaitCommand(this.world);
                commandReturn = waitCommand.execute();
                break;

            default:
                System.out.println("Neplatný příkaz");
                break;
        }
    }

    public void updateGameState() {
        int gameState = world.getGameState();
        if (gameState == 0 && !world.getCurrentTasks().contains("Zmapuj základnu")) {
            world.getCurrentTasks().add("Zmapuj základnu");
            ((MapItem) world.getItem("item_baseMap")).getMappedRoomsID().add("room_commanderRoom");
        }
        if (gameState == 0 && !world.getCharacter("character_tunnelGuard").isMandatoryTalk()) {
            world.setGameState(1);
            world.moveCharacter("character_rudaGuard", "room_hall");
            return;
        }
        if (gameState == 1 && !world.getCharacter("character_rudaGuard").isMandatoryTalk()) {
            if (commandReturn.equals("0")) {
                world.setGameState(2);
                world.moveCharacter("character_rudaGuard", "room_checkpoint");
                world.getCurrentTasks().add("Najdi Rudovu knihu");
                world.getFutureTasks().remove("Najdi Rudovu knihu");
                this.setStartTime(world.getTime());
                world.getRoom("room_bathroom").getItemsID().add("item_rudaBook");
                return;
            }
            world.getPlayer().setSuspiciousness(1);
            world.setGameState(3);
            world.moveCharacter("character_rudaGuard", "room_checkpoint");
            world.getRoom("room_kitchen").getItemsID().add("item_trashBag");
            return;
        }
        if (commandReturn.equals("3")) {
            if (world.getTime() > this.startTime + 24) {
                world.getPlayer().setSuspiciousness(1);
            }
            world.setGameState(3);
            world.getCurrentTasks().removeFirst();
            world.getRoom("room_kitchen").getItemsID().add("item_trashBag");
            return;
        }
        if (gameState == 3 && commandReturn.equals("1")) {
            world.getItem("item_trashBag").setState(2);
        }
        if (gameState == 3 && commandReturn.equals("0")) {
            world.getPlayer().setSuspiciousness(world.getPlayer().getSuspiciousness() + 1);
        }
        if (world.getPlayer().getSuspiciousness() >= 2) {
            ArrayList<String> newEndCommand = new ArrayList<>();
            newEndCommand.add("konec");
            executeCommand(newEndCommand);
        }
        if (commandReturn.equals("4")) {
            world.setCurrentRoom(world.getRoom("room_hall"));
        }
        if (world.getPlayer().getInventoryID().contains("item_trashBag")) {
            world.getCharacter("character_mainDoorGuard").setMandatoryTalk(false);
        } else {
            world.getCharacter("character_mainDoorGuard").setMandatoryTalk(true);
        }
        if (world.getPlayer().getInventoryID().contains("item_baseMap") && ((MapItem) world.getItem("item_baseMap")).getMappedRoomsID().containsAll(world.getRoomsID())) {
            world.getCurrentTasks().remove("Zmapuj základnu");
            world.getCurrentTasks().add("Dostaň mapu k tajné službě");
            world.getFutureTasks().remove("Dostaň mapu k tajné službě");
            (world.getItem("item_baseMap")).setState(4); //state 4 == the map is full
        }
        if (commandReturn.equals("5")) {
            world.setGameState(4);
        }
        if (commandReturn.equals("6") && world.getGameState() == 4) {
            System.out.println("Když jsi spal na chvíli tě vzbudil zvuk houkaček projíždějících okolo, myslel sis že to je náhoda, ale když se to po hodině zopakovalo věděl jsi že ne.");
            world.moveCharacter("character_tomasGuard", "room_street");
        }
        if (world.getPlayer().getSuspiciousness() < 1 && world.getRoom("room_street").getCharactersID().contains("character_tomasGuard")) { //ruda trusts player
            world.setGameState(5);
        }
        if (world.getPlayer().getSuspiciousness() >= 1 && world.getRoom("room_street").getCharactersID().contains("character_tomasGuard")) { //ruda doesn't trust player
            world.setGameState(6);
        }
        if (commandReturn.equals("6")) {
            world.getRoom("room_storage").getItemsID().add("item_pistolGun");
        }
        if (commandReturn.equals("7")) {
            world.getCurrentRoom().getCharactersID().remove("character_rudaGuard");
            world.getRoom("room_commanderRoom").setLocked(false);
        }
        if (gameState == 5 && (commandReturn.equals("0") || commandReturn.equals("1"))) {
            world.getCurrentRoom().getCharactersID().remove("character_rudaGuard");
            world.getRoom("room_commanderRoom").setLocked(false);
        }
        if (world.getCurrentRoom().getId().equals("room_commanderRoom")) {
            System.out.println("Úspěšně jsi zatkl velitele odboje a přivolal na základnu razii, agent Martin Starý byl prezidentem vyznamenán a navždy bude považován za hrdinu!");
            ArrayList<String> newEndCommand = new ArrayList<>();
            newEndCommand.add("konec");
            executeCommand(newEndCommand);
        }
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
        //world.getCharacter("character_tunnelGuard").setMandatoryTalk(false); //temp
        //world.getCharacter("character_rudaGuard").setMandatoryTalk(false); //temp

        while (!this.world.isEnd()) {
            System.out.println("\nAktuální místnost: " + world.getCurrentRoom().getName() + "\n" + world.getCurrentRoom().getDescription());
            System.out.println("Aktuální čas:" + world.getTime() % 24 + "(Celkem uběhlo hodin: " + world.getTime() + ")");
            System.out.println("Vedlejší místnosti: " + world.getCurrentAdjacentRooms());
            System.out.println("Postavy v místnosti: " + world.getCurrentRoomCharacterNames());
            System.out.print(world.currentFoundItemsToString());
            executeCommand(getPlayerCommand());
            updateGameState();
        }
    }
}
