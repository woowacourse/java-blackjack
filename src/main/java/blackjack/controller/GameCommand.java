package blackjack.controller;

public enum GameCommand {
    PLAY("y"),
    STOP("n");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String command) {
        if (PLAY.command.equalsIgnoreCase(command)) {
            return PLAY;
        }
        if (STOP.command.equalsIgnoreCase(command)) {
            return STOP;
        }
        throw new IllegalArgumentException("y 또는 n를 입력해주세요.");
    }

    public boolean isPlay() {
        return this == PLAY;
    }
}
