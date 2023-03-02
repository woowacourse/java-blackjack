package blackjack.domain.card;

import java.util.Arrays;

public enum CardSymbol {
    HEART("하트"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    CLOVER("클로버");

    private final String symbol;

    CardSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static CardSymbol of(String symbolName) {
        return Arrays.stream(CardSymbol.values())
                .filter(cardSymbol -> cardSymbol.symbol.equals(symbolName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 카드심볼 입니다."));
    }
}
