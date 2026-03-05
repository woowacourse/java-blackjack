package blackjack.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Rank {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J");

    private final int value;
    private final String label;

    Rank(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Rank random() {
        List<Rank> ranks = Arrays.asList(values());
        Collections.shuffle(ranks);

        return ranks.getFirst();
    }
}
