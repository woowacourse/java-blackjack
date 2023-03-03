package blackjack.view;

import blackjack.domain.Symbol;

import java.util.Arrays;

public enum SymbolMapper {

    SPADE(Symbol.SPADE, "스페이드"),
    DIAMOND(Symbol.DIAMOND, "다이아몬드"),
    HEART(Symbol.HEART, "하트"),
    CLOVER(Symbol.CLOVER,"클로버");

    private final Symbol symbol;
    private final String value;

    SymbolMapper(Symbol symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static String map(Symbol symbol) {
        return Arrays.stream(values())
                .filter(symbolMapper -> symbolMapper.symbol.equals(symbol))
                .map(SymbolMapper::getValue)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 무늬가 없습니다."));
    }

    public String getValue() {
        return value;
    }
}
