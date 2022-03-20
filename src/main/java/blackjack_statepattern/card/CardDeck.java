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
        List<Card> cards = createNewCards();
        return new CardDeck(new LinkedList<>(cards));
    }

    private static List<Card> createNewCards() {
        List<Card> cards = Card.getAllCard();
        Collections.shuffle(cards);
        return cards;
    }

    public Card draw() {
        checkEmptyDeck();
        return value.poll();
    }

    private void checkEmptyDeck() {
        if (value.isEmpty()) {
            value.addAll(createNewCards());
        }
    }
}
