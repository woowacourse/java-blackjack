package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class RandomCardDeckShuffleStrategy implements CardDeckShuffleStrategy {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
