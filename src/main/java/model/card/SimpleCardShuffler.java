package model.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCardShuffler implements CardShuffler {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        Collections.shuffle(shuffleCards);
        return shuffleCards;
    }
}
