package domain.card.shuffler;

import domain.card.Card;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffler implements CardShuffler {

    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
