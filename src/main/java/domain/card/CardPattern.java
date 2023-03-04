package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardPattern {

    HEART, SPADE, DIAMOND, CLOVER;

    static List<CardPattern> findAllCardPattern() {
        return Arrays.asList(values());
    }
}
