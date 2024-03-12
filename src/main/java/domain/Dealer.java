package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;

    public Dealer(final Deck deck) {
        super(new Name(DEALER_NAME));
        this.deck = deck;
    }

    @Override
    public boolean isNotBust() {
        return calculateScore() < 17;
    }

    public Card drawSingleCard() {
        return deck.draw();
    }

    public List<Card> drawCards(final int size) {
        final List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(deck.draw());
        }
        return cards;
    }
}
