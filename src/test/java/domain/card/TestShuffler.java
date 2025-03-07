package domain.card;

import java.util.List;

public class TestShuffler extends CardShuffler {

    @Override
    public List<Card> shuffle(List<Card> deck) {
        return deck;
    }
}
