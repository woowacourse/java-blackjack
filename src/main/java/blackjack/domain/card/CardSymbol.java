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

    public String getSymbol() {
        return this.symbol;
    }
}
