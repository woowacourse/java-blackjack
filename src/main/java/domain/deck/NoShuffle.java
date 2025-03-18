package domain.deck;

import domain.TrumpCard;
import java.util.List;

public class NoShuffle implements ShuffleStrategy {

    @Override
    public void shuffle(List<TrumpCard> cards) {
    }
}
