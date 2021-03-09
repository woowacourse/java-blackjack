package blackjack.domain.card;

import java.util.EnumMap;
import java.util.Objects;

public class CardSymbol {

    private static final EnumMap<SymbolCandidate, CardSymbol> SYMBOL_CACHE =
        new EnumMap<>(SymbolCandidate.class);

    private String symbol;

    static {
        for (SymbolCandidate symbolCandidate : SymbolCandidate.values()) {
            CardSymbol cardSymbol = new CardSymbol(symbolCandidate);
            SYMBOL_CACHE.put(symbolCandidate, cardSymbol);
        }
    }

    private CardSymbol(SymbolCandidate targetSymbol) {
        this.symbol = targetSymbol.getSymbol();
    }

    public static CardSymbol from(String symbol) {
        SymbolCandidate symbolCandidate = SymbolCandidate.matchSymbol(symbol);
        return SYMBOL_CACHE.get(symbolCandidate);
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
