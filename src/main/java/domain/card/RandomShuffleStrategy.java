package domain.card;

import java.util.Collections;
import java.util.Stack;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(Stack<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}
