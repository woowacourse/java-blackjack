package strategy;

import domain.Card;

import java.util.Collections;
import java.util.List;

public class ShuffleCardsStrategy implements ShuffleStrategy{
    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
