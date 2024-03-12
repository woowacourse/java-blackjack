package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        Collections.shuffle(newCards);
        return newCards;
    }
}
