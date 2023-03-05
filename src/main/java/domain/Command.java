package domain;

import java.util.Arrays;
import java.util.Objects;

public enum Command {
    HIT("y"),
    STAY("n");

    private static final String ERROR_INVALID_COMMAND = "[ERROR] y 혹은 n만 입력하실 수 있습니다.";

    private String value;

    Command(String value) {
        this.value = value;
    }

    public static Command find(String targetCommend) {
        return Arrays.stream(Command.values())
                .filter(command -> Objects.equals(command.value, targetCommend))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INVALID_COMMAND));
    }
}
