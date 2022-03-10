package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cardDeck;

    public CardDeck(LinkedList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card drawCard() {
        return cardDeck.poll();
    }

    public int size() {
        return cardDeck.size();
    }
}
