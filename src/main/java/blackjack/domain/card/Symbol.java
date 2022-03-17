package blackjack.domain.card;

import java.util.Arrays;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private static final String SYMBOL_INPUT_ERROR_MESSAGE = "[ERROR] 존재하지 않는 모양입니다.";

    private final String symbolName;

    Symbol(String symbolName) {
        this.symbolName = symbolName;
    }

    public static Symbol of(String symbolNameInput) {
        return Arrays.stream(Symbol.values())
            .filter(value -> value.symbolName.equals(symbolNameInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(SYMBOL_INPUT_ERROR_MESSAGE));
    }

    public String getSymbolName() {
        return symbolName;
    }
}
