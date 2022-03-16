package blackjack.domain.card.generator;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class TestCardGenerator implements CardGenerateStrategy {

    private final List<Card> cards = new ArrayList<>();

    @Override
    public List<Card> generate() {
        return cards;
    }

    public TestCardGenerator(List<Card> cards) {
        this.cards.addAll(cards);
    }
}
