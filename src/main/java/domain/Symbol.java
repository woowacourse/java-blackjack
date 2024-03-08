package domain;

import java.util.Arrays;

public enum Symbol {
    HEART,
    SPADE,
    CLOVER,
    DIAMOND;

    public static Symbol getSymbol(final int number) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.ordinal() == number)
                .findAny()
                .orElseThrow();
    }
}
