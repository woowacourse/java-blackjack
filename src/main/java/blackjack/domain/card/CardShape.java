package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Stream;

public enum CardShape {
    CLOVER,
    DIA,
    SPADE,
    HEART;

    public static Stream<CardShape> stream() {
        return Arrays.stream(values());
    }
}
