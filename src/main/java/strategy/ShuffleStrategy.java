package strategy;

import domain.card.Card;

import java.util.List;
import java.util.Stack;

public interface ShuffleStrategy {

    Stack<Card> shuffle(List<Card> cards);
}
