package blackjack.domain.card;

import java.util.Stack;

public class CardDistributor {

    private static final String DECK_IS_EMPTY = "카드가 모두 소요됐습니다.";

    private final Stack<Card> deck;

    public CardDistributor(DeckGenerator deckGenerator) {
        deck = deckGenerator.generate();
    }

    public Card distribute() {
        if (isEmpty()) {
            throw new IllegalStateException(DECK_IS_EMPTY);
        }
        return deck.pop();
    }

    private boolean isEmpty() {
        return deck.isEmpty();
    }

    @Override
    public String toString() {
        return "CardDistributor{" +
                "deck=" + deck +
                '}';
    }
}
