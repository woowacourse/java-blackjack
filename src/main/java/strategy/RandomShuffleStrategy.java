package strategy;

import domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public Stack<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        Stack<Card> shuffledCards = new Stack<>();
        shuffledCards.addAll(cards);
        return shuffledCards;
    }
}
