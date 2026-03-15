package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements ShuffleStrategy {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> shuffledCard = new ArrayList<>(cards);
        Collections.shuffle(shuffledCard);
        return Collections.unmodifiableList(shuffledCard);
    }
}
