package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck() {
        cards = CardFactory.create();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getCard(int i) {
        return cards.get(i);
    }
}
