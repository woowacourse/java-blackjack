package domain;

import java.util.Arrays;

public enum Symbol {
    HEART,
    SPACE,
    CLOVER,
    DIAMOND;

    public static Symbol getSymbol(int number) {
        return Arrays.stream(Symbol.values())
                .filter(symbol -> symbol.ordinal() == number)
                .findAny()
                .orElseThrow();
    }
}
