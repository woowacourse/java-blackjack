package blackjack.domain.card;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CardSymbol {

    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private final String symbol;

    CardSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static CardSymbol matchByInput(String input) {
        return Arrays.stream(CardSymbol.values())
            .filter(cardSymbol -> cardSymbol.symbol.equals(input))
            .collect(Collectors.toList())
            .get(0);
    }

    public String getSymbol() {
        return this.symbol;
    }
}
