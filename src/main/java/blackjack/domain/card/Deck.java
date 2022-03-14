package blackjack.domain.card;

import java.util.*;

public class Deck {

    protected static final int INIT_DISTRIBUTE_SIZE = 2;

    private final Stack<Card> bunchOfCards;

    public Deck(CardGenerator cardGenerator) {
        this.bunchOfCards = cardGenerator.generate();
    }

    public Card draw() {
        return bunchOfCards.pop();
    }
}
