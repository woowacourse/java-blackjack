package domain.card.shufflestrategy;

import domain.card.Card;
import java.util.Collections;
import java.util.List;

public class CardShuffler implements ShuffleStrategy {

    @Override
    public void shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
    }
}
