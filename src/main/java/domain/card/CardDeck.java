package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> deck;

    private CardDeck(List<Card> cards) {
        this.deck = new LinkedList<>(cards);
    }

    public static CardDeck from(CardGenerator cardGenerator) {
        return new CardDeck(cardGenerator.generate());
    }

    public Card giveCard() {
        validateDeckIsEmpty();
        return deck.poll();
    }

    private void validateDeckIsEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

}
