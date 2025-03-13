package domain.shuffler;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class RandomShuffler implements Shuffler {
    @Override
    public void shuffle(Deque<Card> cards) {
        List<Card> shuffledList = new ArrayList<>(cards);
        Collections.shuffle(shuffledList);
        cards.clear();
        cards.addAll(shuffledList);
    }
}
