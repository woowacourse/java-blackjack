package blackjack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public List<Card> initCards() {
        List<Card> cardsValue = new ArrayList<>();
        cardsValue.add(pop());
        cardsValue.add(pop());
        return cardsValue;
    }
}
