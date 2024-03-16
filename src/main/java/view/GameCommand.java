package view;

import java.util.Arrays;

public enum GameCommand {

    GET_CARD("Y"),
    REFUSE_CARD("N");

    final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static boolean isStandCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch(gameCommand -> REFUSE_CARD.command.equals(inputCommand));
    }
}
