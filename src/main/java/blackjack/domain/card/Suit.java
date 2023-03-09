package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

public enum Suit {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    public static List<Suit> getAll() {
        return Arrays.asList(values());
    }
}
