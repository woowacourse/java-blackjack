package domain.card;

import domain.CardShuffler;

import java.util.Collections;
import java.util.List;

public class CardRandomShuffler implements CardShuffler {
    @Override
    public List<Card> shuffle(final List<Card> cards) {
        Collections.shuffle(cards);
        return List.copyOf(cards);
    }
}
