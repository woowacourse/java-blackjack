package blackjack.domain.card;

import java.util.Deque;

public class CardDistributor {

    private static final String DECK_IS_EMPTY = "카드가 모두 소요됐습니다.";

    private final DeckGenerator deckGenerator;
    private final Deque<Card> deck;

    public CardDistributor(DeckGenerator deckGenerator) {
        this.deckGenerator = deckGenerator;
        deck = deckGenerator.generateDeck();
    }

    public Card distribute() {
        if (isEmpty()) {
            throw new IllegalArgumentException(DECK_IS_EMPTY);
        }
        return deck.pop();
    }

    private boolean isEmpty() {
        return deck == null || deck.isEmpty();
    }
}
