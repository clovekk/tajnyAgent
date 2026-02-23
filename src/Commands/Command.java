package Commands;

/**
 * This serves as the pattern for all the commands
 * @author Adam Dluhoš
 */
public interface Command {
    /**
     * Executes the command
     * @return a String value that depends on the command and players choices that can be used to relay players choices within the command to other classes
     */
    String execute();
}
