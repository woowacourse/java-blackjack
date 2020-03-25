package blackjack.domain.card;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private Suit suit;
    private Rank rank;

    private Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card from(Suit suit, Rank rank) {
        return CardCache.CARDS.get(rank.name() + suit.name());
    }

    public boolean isAce() {
        return getRankValue() == Rank.ACE.getValue();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return rank.getSymbol() + suit.getName();
    }

    private static class CardCache {
        private static final Map<String, Card> CARDS = new HashMap<>();

        static {
            makeCards();
        }

        private static void makeCards() {
            for (Suit suit : Suit.values()) {
                loop(suit);
            }
        }

        private static void loop(final Suit suit) {
            for (Rank rank : Rank.values()) {
                CARDS.put(rank.name() + suit.name(), new Card(suit, rank));
            }
        }

        private CardCache() {
        }
    }
}
