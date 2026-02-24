package Commands;

import java.util.ArrayList;

//prikazy

/**
 * This command displays all the commands
 * @author Adam Dluhoš
 */
public class HelpCommand implements Command {
    private ArrayList<String> commands;

    public HelpCommand(ArrayList<String> commands) {
        this.commands = commands;
    }

    @Override
    public String execute() {
        String commands = """
                konec                    | ukončí a uloží hru
                prikazy                  | vypíše příkazy
                napoveda                 | vypíše nápovědu k aktuální situaci
                zakresli                 | zakreslí pokoj do mapy pokud ji hráč má
                jdi-<jmeno lokace>       | posune hráče do dané místnosti
                seber-<jmeno predmetu>   | hráč sebere daný předmět
                proheldej                | prohledá aktuální místnost
                spi                      | hráč půjde do rána spát
                mluv-<jmeno postavy>     | promluví s danou postavou
                ukoly                    | zobrazí seznam úkolů
                zahod-<jmeno predmetu>   | zahodí daný předmět pokud to je dovolené
                pouzij-<jmeno predmetu>  | použije daný předmět pokud to je možné
                cekej                    | hráč počká 6 hodin
                """;
        System.out.println(commands);
        return "";
    }
}
