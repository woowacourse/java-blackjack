package domain.game;

import java.util.Arrays;

public enum Command {
    Y("y"),
    N("n");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command from(String value) {
        return Arrays.stream(Command.values())
                .filter(it -> it.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다."));
    }
}
