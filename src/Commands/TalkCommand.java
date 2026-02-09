package Commands;

import game.*;
import game.Character;
import java.util.Scanner;

//mluv
public class TalkCommand implements Command {
    private World world;
    private String characterName;

    public TalkCommand(World world, String characterName) {
        this.world = world;
        this.characterName = characterName;
    }

    @Override
    public void execute() {
        if (!world.getCurrentRoomCharacters().isEmpty()) {
            if (world.getCurrentRoomCharacters().contains(world.getCharacterByCompatibleName(characterName)) && world.getCharacterByCompatibleName(characterName) != null) {
                Character character = world.getCharacterByCompatibleName(characterName);
                Player player = world.getPlayer();
                Scanner scn = new Scanner(System.in);
                //just a test, could potentially replace the original dialogues solution
                //character dialogues list format: (0) = end current dialogue text, (1) = index reserved for potential use, (2 + 5 * gameState) initial character response, (3 + 5 * gameState) first player dialogue option,
                // (4 + 5 * gameState) second player dialogue option, (5 + 5 * gameState) first character response option, (6 + 5 * gameState) second character response option
                //example: {"dialogueEnd", "unassigned", "initialResponse", "1stOption", "2ndOption", "1stResponse", "2ndResponse", "initialResponse2", "1stOption2", "2ndOption2", "1stResponse2", "2ndResponse2"}
                //player dialogues list format: (0) = end current dialogue text, (1) = index reserved for potential use

                try{
                    System.out.println(character.getName() + ": " + character.getDialogues().get(world.getGameState() * 5 + 2));
                    System.out.println("Mozne odpovedi(1-3): " + character.getDialogues().subList(world.getGameState() * 5 + 3, world.getGameState() * 5 + 5) + ", " + player.getDialogues().getFirst());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("S postavou " + character.getName() + " nemáš co si říct");
                    return;
                }

                //player chooses dialogue option
                System.out.print("Zadej číslo odpovědi >>>");
                int chosenOption = scn.nextInt() - 1;
                while (chosenOption < 0 || chosenOption > 2) {
                    System.out.println("Neplatné číslo odpovědi");
                    System.out.print("Zadej číslo odpovědi >>>");
                    chosenOption = scn.nextInt() - 1;
                }

                //player ended dialogue
                if (chosenOption == 2) {
                    System.out.println(player.getName() + ": " + player.getDialogues().getFirst());
                    System.out.println(character.getName() + ": " + character.getDialogues().getFirst());
                    return;
                }

                //player chose an option
                System.out.println(player.getName() + ": " + character.getDialogues().get(3 + chosenOption + world.getGameState() * 5));
                System.out.println(character.getName() + ": " + character.getDialogues().get(5 + chosenOption + world.getGameState() * 5));
                character.setMandatoryTalk(false);
            } else {
                System.out.println("Postava se jménem " + characterName + " v místnosti není");
            }
        } else {
            System.out.println("V této místnosti si nikdo povídat nechce");
        }
    }
}
