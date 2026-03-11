package blackjack.domain;

import java.util.List;
import java.util.Objects;

public enum Answer {

    YES("y"),
    NO("n");

    private final String symbol;

    Answer(String symbol) {
        this.symbol = symbol;
    }

    static List<Answer> all() {
        return List.of(values());
    }

    public static Answer pick(String symbol) {
        return all().stream()
            .filter(answer -> Objects.equals(answer.symbol, symbol))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("대답은 y 혹은 n으로만 해주세요."));
    }
}
