package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Suit {
    SPADE,
    DIAMOND,
    HEART,
    CLUB;

    public static Stream<Suit> stream() {
        return Arrays.stream(values());
    }
}
