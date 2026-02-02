package Commands;

import game.*;
import game.Character;

import java.sql.SQLOutput;
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
            if (world.getCurrentRoomCharacters().contains(world.getCharacterByCompatibleName(characterName))) {
                Character character = world.getCharacterByCompatibleName(characterName);
                Scanner scn = new Scanner(System.in);
                System.out.println(character.getDialogues().get(world.getGameState()));
                System.out.println("Mozne odpovedi: " + world.getPlayer().getDialogues().get(world.getGameState()));
                int chosenOption = scn.nextInt();
                System.out.println(world.getPlayer().getDialogues().get(world.getGameState() * 4));

            } else {
                System.out.println("Postava se jménem " + characterName + " v místnosti není");
            }
        } else {
            System.out.println("V této místnosti si nikdo povídat nechce");
        }
    }
}
