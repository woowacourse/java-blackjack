package blackjack;

import java.util.LinkedList;
import java.util.Queue;

public class FixedCardDeck implements CardDeck {
    private final Queue<Card> cards;

    public FixedCardDeck() {
        cards = new LinkedList<>();
        for (Suits suit : Suits.values()) {
            for (Denominations denomination : Denominations.values()) {
                cards.offer(Card.from(denomination.getName() + suit.getName()));
            }
        }
    }

    @Override
    public Card pop() {
        if (cards.isEmpty()){
            throw new IllegalArgumentException();
        }
        return cards.poll();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
