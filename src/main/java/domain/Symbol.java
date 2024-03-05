package domain;

import java.util.Arrays;

public enum Symbol {
    HEART,
    CLOVER,
    SPACE,
    DIAMOND;

    public static Symbol findBy(int index) {
        return Arrays.stream(values())
                .filter(symbol -> symbol.ordinal() == index)
                .findFirst()
                .orElseThrow();
    }
}
