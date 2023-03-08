package blackjack.controller;

import blackjack.constants.ErrorCode;
import blackjack.controller.exception.InvalidCommandException;

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
        throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
    }

    public boolean isPlay() {
        return this == PLAY;
    }
}
