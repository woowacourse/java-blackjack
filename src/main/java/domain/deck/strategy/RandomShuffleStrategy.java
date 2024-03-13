package domain.deck.strategy;

import domain.card.Card;

import java.util.Collections;
import java.util.Stack;

public class RandomShuffleStrategy implements ShuffleStrategy {


    @Override
    public Stack<Card> shuffle(Stack<Card> decks) {
        Collections.shuffle(decks);
        return decks;
    }


}
