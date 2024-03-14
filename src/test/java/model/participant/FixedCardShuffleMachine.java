package model.participant;

import java.util.LinkedList;
import java.util.Queue;
import model.card.Card;
import model.casino.CardShuffleMachine;

public class FixedCardShuffleMachine implements CardShuffleMachine {
    @Override
    public Queue<Card> shuffleCardDeck() {
        return new LinkedList<>(generateAllCards());
    }

}
