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
        List<Card> cards = createNewCards();
        return new CardDeck(new LinkedList<>(cards));
    }

    private static List<Card> createNewCards() {
        List<Card> cards = Card.createCardDeck();
        Collections.shuffle(cards);
        return cards;
    }

    public Card drawCard() {
        checkEmptyDeck();
        return cards.poll();
    }

    private void checkEmptyDeck() {
        if (cards.isEmpty()) {
            cards.addAll(createNewCards());
        }
    }
}
