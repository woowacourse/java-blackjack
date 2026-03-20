package domain;

import domain.card.Card;
import java.util.Collections;
import java.util.List;

public class RandomDeckShuffler implements DeckShuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);

        return cards;
    }
}
