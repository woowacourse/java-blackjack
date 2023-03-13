package domain.card;

import java.util.Collections;
import java.util.LinkedList;

public class CardDeck {

    private final LinkedList<Card> cardDeck = new LinkedList<>();

    {
        init();
    }

    private void init() {
        for (Suit suit : Suit.values()) {
            initNumber(suit);
        }
    }

    private void initNumber(Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            cardDeck.add(Card.of(suit, denomination));
        }
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public Card pick() {
        return cardDeck.pollLast();
    }
}
