package blackjack.domain;

import java.util.Arrays;
import java.util.List;

public enum Symbol {
    SPADE,
    DIAMOND,
    HEART,
    CLOVER;

    public static List<Symbol> getAll() {
        return Arrays.asList(values());
    }
}
