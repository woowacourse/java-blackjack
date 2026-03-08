package domain.deck;

import domain.Card;

import java.util.Collections;
import java.util.List;

public class RandomShuffle implements Shuffle {

    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
