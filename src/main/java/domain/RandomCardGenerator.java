package domain;

import java.util.Collections;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {

    @Override
    public List<Card> shuffle() {
        final List<Card> cards = generate();
        Collections.shuffle(cards);
        return cards;
    }

    @Override
    public List<Card> generate() {
        return CardGenerator.super.generate();
    }
}
