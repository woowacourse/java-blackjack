package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CardSymbol {

    private static final Map<String, CardSymbol> SYMBOL_CACHE = new HashMap<>();
    private final String symbol;

    static {
        for (SymbolCandidate symbolCandidate : SymbolCandidate.values()) {
            CardSymbol cardSymbol = new CardSymbol(symbolCandidate);
            SYMBOL_CACHE.put(symbolCandidate.getSymbol(), cardSymbol);
        }
    }

    private CardSymbol(SymbolCandidate targetSymbol) {
        this.symbol = targetSymbol.getSymbol();
    }

    public static CardSymbol from(String symbol) {
        return SYMBOL_CACHE.get(symbol);
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardSymbol that = (CardSymbol) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
