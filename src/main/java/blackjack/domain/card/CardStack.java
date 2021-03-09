package blackjack.domain.card;

import java.util.*;

public class CardStack {

    private final Deque<Card> cards;

    private CardStack(final List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardStack create() {
        return new CardStack(Card.makeAllCards());
    }

    public List<Card> getTwoCards() {
        return new ArrayList<>(
                Arrays.asList(cards.pop(), cards.pop())
        );
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card getSingleCard() {
        return cards.pop();
    }
}
