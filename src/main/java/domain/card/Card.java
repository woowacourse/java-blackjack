package domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Card {

    private static final Map<String, Card> CACHE = new HashMap<>();

    private final Rank rank;
    private final Symbol symbol;

    public Card(Rank rank, Symbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static Card of(Rank rank, Symbol symbol) {
        if (CACHE.isEmpty()) {
            cache();
        }
        if (CACHE.containsKey(rank.name() + symbol.name())) {
            return CACHE.get(rank.name() + symbol.name());
        }
        return new Card(rank, symbol);
    }

    private static void cache() {
        for (Symbol symbol : Symbol.values()) {
            cacheAllCardsWithSymbol(symbol);
        }
    }

    private static void cacheAllCardsWithSymbol(Symbol symbol) {
        for (Rank rank : Rank.values()) {
            CACHE.put(rank.name() + symbol.name(), new Card(rank, symbol));
        }
    }

    public Score score() {
        return rank.getScore();
    }

    public Rank rank() {
        return rank;
    }

    public Symbol symbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Card) obj;
        return Objects.equals(this.rank, that.rank) &&
            Objects.equals(this.symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, symbol);
    }

    @Override
    public String toString() {
        return "Card[" +
            "rank=" + rank + ", " +
            "symbol=" + symbol + ']';
    }
}
