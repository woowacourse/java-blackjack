package domain.deck;

import domain.TrumpCard;
import java.util.List;

@FunctionalInterface
public interface ShuffleStrategy {

    void shuffle(List<TrumpCard> cards);
}
