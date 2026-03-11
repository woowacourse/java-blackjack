package domain.strategy;

import domain.card.Card;

import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        return cards;
    }
}
