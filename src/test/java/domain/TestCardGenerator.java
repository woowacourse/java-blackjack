package domain;

import java.util.List;

public class TestCardGenerator implements CardGenerator {

    static List<Card> cards;

    static {
        cards = CardGenerator.generate();
    }

    @Override
    public List<Card> shuffle() {
        return cards;
    }
}
