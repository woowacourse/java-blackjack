package domain.deck.strategy;

import domain.card.Card;

import java.util.Stack;

public interface ShuffleStrategy {

    Stack<Card> shuffle(Stack<Card> cards);
}
