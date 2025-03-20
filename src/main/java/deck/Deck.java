package deck;

import card.Card;
import java.util.ArrayDeque;
import java.util.Deque;

public class Deck {

    private final Deque<Card> cards;

    public Deck(DeckShuffleStrategy deckShuffleStrategy) {
        this.cards = new ArrayDeque<>(deckShuffleStrategy.createAllCards());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[ERROR] 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
