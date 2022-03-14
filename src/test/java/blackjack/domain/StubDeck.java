package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.LinkedList;
import java.util.List;

public class StubDeck {

    private final LinkedList<Card> cards;

    public StubDeck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}
