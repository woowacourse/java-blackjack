package domain;

import java.util.Arrays;
import java.util.List;

public enum Symbol {
    DIAMOND,SPADE,CLOVER,HEART;

    public static List<Symbol> getAllSymbols() {
        return Arrays.stream(values()).toList();
    }
}
