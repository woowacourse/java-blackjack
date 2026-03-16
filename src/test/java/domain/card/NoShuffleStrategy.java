package domain.card;

import java.util.List;

public class NoShuffleStrategy implements ShuffleStrategy {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        return List.copyOf(cards);
    }
}
