package domain.card;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Card {
    private final Symbol symbol;
    private final Rank rank;

    private static final Map<String, Card> CACHE = new ConcurrentHashMap<>();

    private Card(final Symbol symbol, final Rank rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public static Card of(final Symbol symbol, final Rank rank) {
        return CACHE.computeIfAbsent(toKey(symbol, rank), key -> new Card(symbol, rank));
    }

    private static String toKey(final Symbol symbol, final Rank rank) {
        return symbol.name() + rank.name();
    }

    public int getScore() {
        return rank.getScore();
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return symbol == card.symbol && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, rank);
    }
}
