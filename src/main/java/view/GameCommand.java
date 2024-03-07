package view;

import java.util.Arrays;

public enum GameCommand {

    GET_CARD("Y"),
    REFUSE_CARD("N");

    final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static boolean isGetCardCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch(gameCommand -> GET_CARD.command.equals(inputCommand));
    }
}
