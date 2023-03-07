package blackjack.domain;

public enum Command {
    HIT("y"),
    BUST("n");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isHit(String command) {
        return HIT.getCommand() == command;
    }

    private String getCommand() {
        return command;
    }
}
