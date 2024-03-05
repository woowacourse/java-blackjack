package blackjack.model;

import java.util.Arrays;
import java.util.List;

public enum CardPattern {

    SPADE,
    HEART,
    DIAMOND,
    CLOVER;

    CardPattern() {
    }

    public static List<CardPattern> getCardPattern() {
        return Arrays.asList(values());
    }
}
