package model;

import java.util.LinkedList;
import java.util.Queue;

public class FixedCardShuffleMachine implements CardShuffleMachine{
    @Override
    public Queue<Card> shuffleCardDeck() {
        return new LinkedList<>(generateAllCards());
    }

}
