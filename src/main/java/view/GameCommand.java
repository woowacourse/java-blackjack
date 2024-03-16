package view;

import java.util.Arrays;

public enum GameCommand {

    HIT("Y"),
    STAND("N");

    final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static boolean isHitCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch(gameCommand -> HIT.command.equals(inputCommand));
    }
}
