package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public final class RandomCardGenerator implements CardGenerator {

    private final static List<Card> cards = CardGenerator.generate();

    @Override
    public List<Card> shuffle() {
        Collections.shuffle(cards);
        return cards;
    }

}
