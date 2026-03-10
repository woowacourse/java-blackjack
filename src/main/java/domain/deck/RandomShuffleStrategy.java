package domain.deck;

import domain.card.Card;

import java.util.Collections;
import java.util.List;

public class RandomShuffleStrategy implements CardShuffleStrategy {
    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
