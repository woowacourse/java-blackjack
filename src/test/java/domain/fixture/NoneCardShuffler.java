package domain.fixture;

import domain.card.Card;
import domain.card.CardShuffler;

import java.util.List;

public class NoneCardShuffler implements CardShuffler {

    private final List<Card> shuffled;

    public NoneCardShuffler(final List<Card> shuffled) {
        this.shuffled = shuffled;
    }

    @Override
    public List<Card> shuffle(final List<Card> cards) {
        return shuffled;
    }
}
