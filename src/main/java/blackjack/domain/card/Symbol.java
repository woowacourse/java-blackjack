package blackjack.domain.card;

import java.util.Arrays;

public enum Symbol {
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드"),
    CLOVER("클로버");

    private final String symbolName;

    Symbol(String symbolName){
        this.symbolName = symbolName;
    }

    public static Symbol of(String symbolNameInput){
        return Arrays.stream(Symbol.values())
                .filter(value -> value.symbolName.equals(symbolNameInput))
                .findAny()
                .orElseThrow();
    }

    public String getSymbolName() {
        return symbolName;
    }
}
