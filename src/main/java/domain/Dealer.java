package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {
    public static final int DEALER_HIT_THRESHOLD = 17;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;

    public Dealer(final Deck deck) {
        super(new Name(DEALER_NAME));
        this.deck = deck;
    }

    @Override
    public boolean isNotBust() {
        return score() < DEALER_HIT_THRESHOLD;
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
