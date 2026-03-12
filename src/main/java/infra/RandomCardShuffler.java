package infra;

import domain.Card;
import domain.CardShuffler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        Collections.shuffle(shuffleCards);
        return shuffleCards;
    }
}
