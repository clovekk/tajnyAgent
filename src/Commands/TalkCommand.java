package Commands;

import game.*;
import game.Character;

import java.util.InputMismatchException;
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
    public String execute() {
        if (!world.getCurrentRoomCharacters().isEmpty()) {
            if (world.getCurrentRoomCharacters().contains(world.getCharacterByCompatibleName(characterName)) && world.getCharacterByCompatibleName(characterName) != null) {
                Character character = world.getCharacterByCompatibleName(characterName);
                Player player = world.getPlayer();
                Scanner scn = new Scanner(System.in);
                int dialogueCoefficient = getDialogueCoefficient(character);
                //System.out.println("pre: " + dialogueCoefficient);
                if (character.getDialogues().get(dialogueCoefficient * 5 + 2).equals("unsp")) {
                    dialogueCoefficient = Integer.parseInt(character.getDialogues().get(1));
                }
                //System.out.println("aft: " + dialogueCoefficient);

                if (world.getGameState() == 2 && character.getId().equals("character_rudaGuard") && world.getPlayer().getInventoryID().contains("item_rudaBook")) {
                    System.out.println(character.getName() + ": Díky že jsi tu knihu našel.");
                    System.out.println(player.getName() + ": Není zač.");
                    return "3";
                }

                if (character.getId().equals("character_mainDoorGuard") && !player.getInventoryID().contains("item_trashBag")) {
                    System.out.println(character.getName() + ": Tudy nikam jít nemůžeš!");
                    System.out.println(player.getName() + ": Promiň, spletl jsem si dveře.");
                    return "4";
                }

                if (character.getId().equals("character_rudaGuard") && world.getGameState() == 6) {
                    System.out.println("Nemám na tebe čas!");
                    return "6";
                }

                //character dialogues list format: (0) = end current dialogue text, (1) = gameState value of last unique dialogue, (2 + 5 * gameState) initial character response, (3 + 5 * gameState) first player dialogue option,
                // (4 + 5 * gameState) second player dialogue option, (5 + 5 * gameState) first character response option, (6 + 5 * gameState) second character response option
                //the gameState value the dialog used by the character when they no longer have an important role in the story and have no unique dialogues
                //example: {"dialogueEnd", "unassigned", "initialResponse", "1stOption", "2ndOption", "1stResponse", "2ndResponse", "initialResponse2", "1stOption2", "2ndOption2", "1stResponse2", "2ndResponse2"}
                //player dialogues list format: (0) = end current dialogue text

                try{
                    System.out.println(character.getName() + ": " + character.getDialogues().get(dialogueCoefficient * 5 + 2));
                    System.out.println("Mozne odpovedi(1-3): " + character.getDialogues().subList(dialogueCoefficient * 5 + 3, dialogueCoefficient * 5 + 5) + ", " + player.getDialogues().getFirst());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("S postavou " + character.getName() + " nemáš co si říct");
                    return "";
                }
                int chosenOption = -1;

                //player chooses dialogue option
                System.out.print("Zadej číslo odpovědi >>>");
                try {
                    chosenOption = scn.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Toto není číslo");
                }
                while (chosenOption < 0 || chosenOption > 2) {
                    System.out.println("Neplatné číslo odpovědi");
                    System.out.print("Zadej číslo odpovědi >>>");
                    try {
                        chosenOption = scn.nextInt() - 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Toto není číslo");
                    }

                }

                //player ended dialogue
                if (chosenOption == 2) {
                    System.out.println(player.getName() + ": " + player.getDialogues().getFirst());
                    System.out.println(character.getName() + ": " + character.getDialogues().getFirst());
                    world.setTime(world.getTime() + 1);
                    return "2";
                }

                //player chose an option
                System.out.println(player.getName() + ": " + character.getDialogues().get(3 + chosenOption + dialogueCoefficient * 5));
                System.out.println(character.getName() + ": " + character.getDialogues().get(5 + chosenOption + dialogueCoefficient * 5));
                character.setMandatoryTalk(false);
                world.setTime(world.getTime() + 1);
                return String.valueOf(chosenOption);
            } else {
                System.out.println("Postava se jménem " + characterName + " v místnosti není");
            }
        } else {
            System.out.println("V této místnosti si nikdo povídat nechce");
        }
        return "";
    }

    private int getDialogueCoefficient(Character character) {
        int dialogueCoefficient = Integer.parseInt(character.getDialogues().get(1));
        //System.out.println("wor: " + world.getGameState());
        if (dialogueCoefficient > world.getGameState()) {
            dialogueCoefficient = world.getGameState();
        }
        return dialogueCoefficient;
    }
}
