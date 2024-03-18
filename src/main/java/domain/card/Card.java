package domain.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Card {

    private static final Map<String, Card> CACHE = new HashMap<>();

    private final Rank rank;
    private final Symbol symbol;

    private Card(Rank rank, Symbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static Card of(Rank rank, Symbol symbol) {
        String cardName = rank.name() + symbol.name();

        if (CACHE.containsKey(cardName)) {
            return CACHE.get(cardName);
        }
        CACHE.put(cardName, new Card(rank, symbol));
        return CACHE.get(cardName);
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
