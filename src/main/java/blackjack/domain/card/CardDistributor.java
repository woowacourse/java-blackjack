package blackjack.domain.card;

import java.util.*;

public class CardDistributor {

    private static final String DECK_IS_EMPTY = "카드가 모두 소요됐습니다.";

    private final Deque<Card> deck;

    public CardDistributor() {
        List<Card> cards = Card.generateDeck();
        Collections.shuffle(cards);
        this.deck = new ArrayDeque<>(cards);
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

    @Override
    public String toString() {
        return "CardDistributor{" +
                "deck=" + deck +
                '}';
    }
}
