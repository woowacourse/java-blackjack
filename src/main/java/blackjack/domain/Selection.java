package blackjack.domain;

import java.util.Arrays;

public enum Selection {
    NO("n"),
    YES("y");

    private final String value;

    Selection(String value) {
        this.value = value;
    }

    public static Selection from(String value) {
        return Arrays.stream(Selection.values())
            .filter(s -> s.value.equalsIgnoreCase(value))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력하여야 합니다."));
    }
}
