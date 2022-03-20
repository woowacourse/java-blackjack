package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Card {

    private final CardRank rank;
    private final CardSymbol symbol;

    private Card(final CardRank rank, final CardSymbol symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public static Card of(final CardRank rank, final CardSymbol symbol) {
        return CardCache.getCache(rank, symbol);
    }

    public boolean isAce() {
        return rank == CardRank.ACE;
    }

    public Score getRankValue() {
        return rank.getValue();
    }

    public String getName() {
        return symbol.getDisplayName() + rank.getDisplayName();
    }

    @Override
    public String toString() {
        return "Card{" + getName() + "}";
    }

    private static class CardCache {
        static Map<String, Card> cache = new HashMap<>(52);

        static Card getCache(final CardRank rank, final CardSymbol symbol) {
            return cache.computeIfAbsent(toKey(rank, symbol), (s) -> new Card(rank, symbol));
        }

        static String toKey(final CardRank rank, final CardSymbol symbol) {
            return symbol.getDisplayName() + rank.getDisplayName();
        }
    }
}
