package blackjack.view;

import java.util.Arrays;

public enum PlayerAnswer {

    YES("y"),
    NO("n");

    private final String value;

    PlayerAnswer(final String value) {
        this.value = value;
    }

    public static PlayerAnswer from(String input) {
        return Arrays.stream(values())
                .filter(it -> it.value.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y, n 이외의 값이 입력되었습니다."));
    }

    public boolean isDraw() {
        return this == YES;
    }
}
