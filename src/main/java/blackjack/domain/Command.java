package blackjack.domain;

public enum Command {
    HIT("y"),
    BUST("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
