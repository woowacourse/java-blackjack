package domain.card;

import java.util.Collections;
import java.util.List;

public final class RandomCardGenerator implements CardGenerator {

    private static final List<Card> cards;

    static {
        cards = CardGenerator.generate();
    }

    @Override
    public List<Card> shuffle() {
        Collections.shuffle(cards);
        return cards;
    }

}
