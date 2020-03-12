package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();
        CardCache.cards.forEach(cards::push);
        return cards;
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
