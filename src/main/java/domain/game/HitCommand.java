package domain.game;

import java.util.Arrays;

public enum HitCommand {
    y("y"),
    n("n");

    private static final String WRONG_COMMAND_ERROR_MESSAGE = "[ERROR] 잘못된 명령어 입니다.";

    private final String value;

    HitCommand(String value) {
        this.value = value;
    }

    public static HitCommand findCommand(String input) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_ERROR_MESSAGE));
    }

}
