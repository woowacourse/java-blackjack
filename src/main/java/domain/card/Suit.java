package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Suit {
    HEARTS,
    DIAMONDS,
    SPADES,
    CLUBS;

    public static List<Suit> getValues() {
        return Arrays.stream(Suit.values())
                .toList();
    }
}
