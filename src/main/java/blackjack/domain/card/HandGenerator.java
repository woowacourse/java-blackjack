package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandGenerator {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;

    public HandGenerator(Deck deck) {
        this.deck = deck;
    }

    public Hand generate() {
        List<Card> cards = new ArrayList<>();
        while (cards.size() < INITIAL_CARD_COUNT) {
            cards.add(deck.drawCard());
        }

        return new Hand(cards);
    }
}
