package blackjack.domain;

import java.util.Arrays;

public enum DrawCommand {
    YES("y"),
    NO("n"),
    ;

    private final String value;

    DrawCommand(final String value) {
        this.value = value;
    }

    public static DrawCommand from(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 커맨드입니다."));
    }
}
