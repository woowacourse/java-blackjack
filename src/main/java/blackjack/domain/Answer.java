package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Answer {

    YES("y"),
    NO("n");

    private final String symbol;

    Answer(String symbol) {
        this.symbol = symbol;
    }

    public static Answer pick(String symbol) {
        return Arrays.stream(values())
            .filter(answer -> Objects.equals(answer.symbol, symbol))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("대답은 y 혹은 n으로만 해주세요."));
    }
}
