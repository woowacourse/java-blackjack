package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        ArrayList<Card> cardsShuffle = new ArrayList<>(cards);
        Collections.shuffle(cardsShuffle);

        return List.copyOf(cardsShuffle);
    }
}
