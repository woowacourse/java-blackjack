package domain.card;

import java.util.Collections;
import java.util.Stack;

import domain.card.Card;
import domain.card.ShuffleStrategy;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(Stack<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}
