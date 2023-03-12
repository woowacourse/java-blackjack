package domain.card;

import java.util.Stack;

import view.ErrorMessage;

public final class Deck {
    private final Stack<Card> deck;

    private Deck(final Stack<Card> deck) {
        this.deck = deck;
    }

    public static Deck from(final ShuffleStrategy shuffleStrategy) {
        Stack<Card> deck = new Stack<>();

        for (Suit suit : Suit.values()) {
            makeCard(deck, suit);
        }

        return new Deck(shuffleStrategy.shuffle(deck));
    }

    private static void makeCard(final Stack<Card> deck, final Suit suit) {
        for (Rank rank : Rank.values()) {
            Card card = Card.from(suit, rank);
            deck.add(card);
        }
    }

    public Card draw() {
        if (deck.empty()) {
            throw new IllegalArgumentException(ErrorMessage.DECK_IS_EMPTY.getMessage());
        }
        return deck.pop();
    }
}
