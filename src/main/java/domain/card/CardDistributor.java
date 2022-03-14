package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDistributor {

    private static final String DECK_IS_EMPTY = "카드가 모두 소요됐습니다.";
    private static final List<Card> CACHE = new ArrayList<>();

    private final Stack<Card> deck = new Stack<>();

    static {
        for (Suit suit : Suit.values()) {
            for (Number number : Number.values()) {
                CACHE.add(new Card(number, suit));
            }
        }
    }

    public CardDistributor() {
        Collections.shuffle(CACHE);
        deck.addAll(CACHE);
    }

    public Card distribute() {
        if (isEmpty()) {
            throw new IllegalArgumentException(DECK_IS_EMPTY);
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
