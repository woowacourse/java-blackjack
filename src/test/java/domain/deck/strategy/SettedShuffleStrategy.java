package domain.deck.strategy;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class SettedShuffleStrategy implements ShuffleStrategy {
    private final List<Card> cards;

    public SettedShuffleStrategy(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public Stack<Card> shuffle(Stack<Card> cards) {
        cards.removeAll(this.cards);
        for (final Card card : this.cards) {
            cards.push(card);
        }
        return cards;
    }
}
