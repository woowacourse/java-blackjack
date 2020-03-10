package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static List<Card> createCards() {
        return new ArrayList<>(CardCache.cards);
    }

    @Override
    public String toString() {
        return "Card{" +
            "suit=" + suit +
            ", rank=" + rank +
            '}';
    }

    private static class CardCache {
        static final List<Card> cards = new ArrayList<>();

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
                cards.add(new Card(suit, rank));
            }
        }

        private CardCache() {
        }
    }
}
