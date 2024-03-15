package view;

import java.util.Arrays;

public enum GameCommand {
    YES("y"),
    NO("n");

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static GameCommand from(final String command) {
        return Arrays.stream(values())
                .filter(value -> command.equals(value.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("%s또는 %s을 입력해주세요.".formatted(YES.value, NO.value)));
    }

    public boolean yes() {
        return this == YES;
    }
}
