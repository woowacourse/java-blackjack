package view;

import domain.card.Symbol;

import java.util.Arrays;
import java.util.function.Supplier;

public enum SymbolView {

    HEART("하트"),
    CLOVER("클로버"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private static final String UNKNOWN_SYMBOL = "존재하지 않는 문양입니다.";
    private final String viewName;

    SymbolView(final String viewName) {
        this.viewName = viewName;
    }

    public static String findName(final Symbol symbol){
        return Arrays.stream(values())
                .filter(symbolView -> symbolView.name().equals(symbol.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_SYMBOL))
                .viewName;
    }
}
