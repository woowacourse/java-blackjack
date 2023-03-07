package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int FIRST_CARD = 0;
    private static final int DECK_SIZE = 52;

    private static final List<Card> deck = new ArrayList<>(DECK_SIZE);

    public Deck() {
        for (Number number : Number.values()) {
            createCard(number);
        }
    }

    private void createCard(final Number number) {
        for (Suit suit : Suit.values()) {
            deck.add(Card.of(number, suit));
        }
    }

    public Card drawACard() {
        Collections.shuffle(deck);
        return deck.remove(FIRST_CARD);
    }

    public boolean containsCard(Card card) {
        return deck.contains(card);
    }
}
