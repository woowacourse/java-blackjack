package domain.card;

import java.util.Stack;

import domain.card.Card;

public interface ShuffleStrategy {
    Stack<Card> shuffle(Stack<Card> deck);
}
