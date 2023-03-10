package blackjack.domain.card;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    static final String DECK_EMPTY_EXCEPTION_MESSAGE = "덱이 비어있어서 카드를 더 뽑을 수 없습니다.";
    private final Queue<Card> cards;

    public Deck(DeckGenerator deckGenerator) {
        this.cards = new LinkedList<>(deckGenerator.generate());
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(DECK_EMPTY_EXCEPTION_MESSAGE);
        }
        return cards.poll();
    }
}
