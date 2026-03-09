package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements CardShuffleStrategy {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        Collections.shuffle(shuffleCards);
        return shuffleCards;
    }
}
