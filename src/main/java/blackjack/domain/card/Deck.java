package blackjack.domain.card;

import java.util.*;

public class Deck {

    private final Stack<Card> bunchOfCards;

    public Deck(CardGenerator cardGenerator) {
        this.bunchOfCards = cardGenerator.generate();
    }

    public Card draw() {
        return bunchOfCards.pop();
    }
}
