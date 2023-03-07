package blackjack.controller;

import java.util.Arrays;

public enum GameCommand {
    PLAY("y"),
    STOP("n");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(String input) {
        validateBlank(input);
        return Arrays.stream(GameCommand.values())
                     .filter(command -> input.equals(command.command))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
    }

    private static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("y 또는 n을 입력해주세요.");
        }
    }

    public boolean isPlay() {
        return this == PLAY;
    }
}
