package blackjack.strategy;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCardShuffle implements CardShuffle {

    @Override
    public List<Card> shuffle(List<Card> cards) {
        ArrayList<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }
}
