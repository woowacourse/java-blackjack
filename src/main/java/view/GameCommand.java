package view;

import java.util.Arrays;

public enum GameCommand {

    HIT("Y"),
    STAY("N");

    final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static boolean isGetCardCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch(gameCommand -> HIT.command.equals(inputCommand));
    }
}
