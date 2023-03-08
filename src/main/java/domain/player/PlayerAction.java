package domain.player;

import java.util.Arrays;

public enum PlayerAction {
    HIT("y"),
    HOLD("n"),
    ;

    private static final String COMMAND_INPUT_ERROR = "y와 n만 입력 가능합니다.";

    private final String command;

    PlayerAction(String command) {
        this.command = command;
    }

    public static PlayerAction from(String input) {
        return Arrays.stream(PlayerAction.values())
                .filter(c -> c.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_INPUT_ERROR));
    }
}
