package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomDeckShuffler implements DeckShuffler {
    @Override
    public List<Card> shuffle(List<Card> cards) {
        List<Card> copied = new ArrayList<>(cards);
        Collections.shuffle(copied);

        return copied;
    }
}
