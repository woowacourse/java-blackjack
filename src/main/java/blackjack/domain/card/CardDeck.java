package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cards;

    private CardDeck(Queue<Card> cards) {
        this.cards = cards;
    }

    public static CardDeck createNewCardDeck() {
        List<Card> cards = Card.createNewCardDeck();
        Collections.shuffle(cards);
        return new CardDeck(new LinkedList<>(cards));
    }

    public Card drawCard() {
        return cards.poll();
    }
}
