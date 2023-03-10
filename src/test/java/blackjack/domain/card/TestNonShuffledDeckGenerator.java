package blackjack.domain.card;

import java.util.LinkedList;
import java.util.List;

public class TestNonShuffledDeckGenerator implements DeckGenerator {

    private final List<Card> cards;

    public TestNonShuffledDeckGenerator(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    @Override
    public List<Card> generate() {
        return cards;
    }
}
