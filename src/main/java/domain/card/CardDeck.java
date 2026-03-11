package domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CardDeck {
    private final Deque<Card> cardDeck = new ArrayDeque<>();

    public CardDeck(List<Card> cards) {
        addAllCards(cards);
    }

    private void addAllCards(List<Card> cards) {
        cardDeck.addAll(cards);
    }

    public Card draw() {
        return cardDeck.pop();
    }
}
