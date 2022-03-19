package blackjack_statepattern.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> value;

    private CardDeck(Queue<Card> value) {
        this.value = value;
    }

    public static CardDeck createNewCardDeck() {
        List<Card> cardDeck = Card.getAllCard();
        Collections.shuffle(cardDeck);
        return new CardDeck(new LinkedList<>(cardDeck));
    }

    public Card draw() {
        return value.poll();
    }
}
