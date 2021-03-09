package blackjack.domain.card;

import blackjack.exception.BlackJackException;
import java.util.Arrays;

public enum SymbolCandidate {

    CLOVER("클로버"),
    HEART("하트"),
    SPADE("스페이드"),
    DIAMOND("다이아몬드");

    private String symbol;

    SymbolCandidate(String symbol) {
        this.symbol = symbol;
    }

    public static SymbolCandidate matchSymbol(String inputSymbol) throws BlackJackException {
        return Arrays.stream(values())
            .filter(element -> element.isSameNumber(inputSymbol))
            .findAny()
            .get();
    }

    private boolean isSameNumber(String inputSymbol) {
        return this.symbol.equals(inputSymbol);
    }

    public String getSymbol() {
        return this.symbol;
    }
}
