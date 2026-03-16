package blackjack.domain.command;

public enum Command {

    YES("y");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isYes(String command) {
        return YES.command.equals(command);
    }

}
