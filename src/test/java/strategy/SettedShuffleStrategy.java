package strategy;

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
    public Stack<Card> shuffle(List<Card> cards) {
        cards.removeAll(this.cards);
        cards.addAll(this.cards);
        Stack<Card> settedCards = new Stack<>();
        settedCards.addAll(cards);
        return settedCards;
    }
}
