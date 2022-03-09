package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;

public enum DrawCommand {
    YES("y"),
    NO("n"),
    ;

    private final String value;

    DrawCommand(final String value) {
        this.value = value;
    }

    public static DrawCommand from(final String input) {
        Objects.requireNonNull(input, "커맨드에는 null이 들어올 수 없습니다.");
        return Arrays.stream(values())
                .filter(command -> command.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 커맨드입니다."));
    }

    public boolean isNo() {
        return this == NO;
    }
}
