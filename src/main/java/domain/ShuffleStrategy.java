package domain;

import java.util.Stack;

public interface ShuffleStrategy {
    public Stack<Card> shuffle(Stack<Card> deck);
}
