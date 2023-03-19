package domain.strategy;

import domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<Card> makeShuffledCards(final List<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }
}
