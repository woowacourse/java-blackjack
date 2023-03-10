package domain.blackjack;

import java.util.Arrays;

public enum BlackjackAction {
    HIT("y"),
    HOLD("n"),
    ;

    private static final String COMMAND_INPUT_ERROR_MESSAGE = "y와 n만 입력 가능합니다.";

    private final String commandValue;

    BlackjackAction(String commandValue) {
        this.commandValue = commandValue;
    }

    public static BlackjackAction from(String commandValue) {
        return Arrays.stream(BlackjackAction.values())
                .filter(c -> c.commandValue.equals(commandValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_INPUT_ERROR_MESSAGE));
    }
}
