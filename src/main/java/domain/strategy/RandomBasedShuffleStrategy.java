package domain.strategy;

import domain.card.Card;

import java.util.Collections;
import java.util.Stack;

public class RandomBasedShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(Stack<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
