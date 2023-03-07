package blackjack.controller;

import blackjack.controller.exception.NotCommandException;

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
        throw new NotCommandException();
    }

    public boolean isPlay() {
        return this == PLAY;
    }
}
