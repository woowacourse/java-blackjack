package domain;

import java.util.HashSet;
import java.util.Set;

public class Deck {
    private final Set<Card> cards = new HashSet<>();

    public Card draw() {
        while (true) {
            final Card card = Card.makeRandomCard(new RandomNumberGeneartor(0, 13));
            final boolean hasNoCard = cards.add(card);
            if (hasNoCard) {
                return card;
            }
        }
    }
}

