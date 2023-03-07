package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class MockDeckGenerator implements DeckGenerator {

    private final List<Card> cards;

    public MockDeckGenerator(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Deck generate() {
        return new Deck(cards);
    }
}
