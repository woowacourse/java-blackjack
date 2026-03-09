package domain;

import domain.card.Card;
import domain.deck.CardDeck;
import java.util.List;

public class StubDeck implements CardDeck {
    private final List<Card> deck;
    private int index;

    public StubDeckImplTest(List<Card> deck) {
        this.deck = deck;
        this.index = 0;
    }

    public Card deal() {
        return deck.get(index++);
    }
}
