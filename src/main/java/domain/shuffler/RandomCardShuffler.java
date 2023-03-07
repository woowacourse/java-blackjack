package domain.shuffler;

import domain.Card;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
