package view;

import java.util.Arrays;
import java.util.Objects;

public enum Decision {

    HIT("y"),
    STAY("n"),
    ;

    private final String input;

    Decision(String input) {
        this.input = input;
    }

    public static Decision from(String input) {
        return Arrays.stream(values())
                .filter(decision -> Objects.equals(decision.input, input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n만 입력해주세요."));
    }
}
