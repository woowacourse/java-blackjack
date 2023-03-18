package domain.card;

import java.util.Collections;
import java.util.Stack;

public final class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(final Stack<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}
