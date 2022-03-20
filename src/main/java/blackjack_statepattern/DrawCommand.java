package blackjack_statepattern;

import java.util.Arrays;

public enum DrawCommand {
    HIT("y"),
    STAY("n");

    private final String value;

    DrawCommand(String inputValue) {
        this.value = inputValue;
    }

    public static DrawCommand from(String value) {
        return Arrays.stream(values())
                .filter(drawCommand -> drawCommand.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 지원하지 않는 커맨드입니다."));
    }

    public boolean isStay() {
        return value == STAY.value;
    }
}
