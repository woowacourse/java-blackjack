package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckGenerator;
import java.util.LinkedList;
import java.util.List;

public class TestDeckGenerator implements DeckGenerator {

    private final List<Card> cards;

    public TestDeckGenerator(final List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    @Override
    public List<Card> generate() {
        return cards;
    }
}
