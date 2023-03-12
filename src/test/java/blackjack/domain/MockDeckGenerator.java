package blackjack.domain;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGenerator;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.IntStream;

public class MockDeckGenerator implements DeckGenerator {
    private final List<Card> cards;

    public MockDeckGenerator(Card card, int count) {
        this.cards = IntStream.range(0, count)
                .mapToObj(v -> card)
                .collect(toList());
    }

    public MockDeckGenerator(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Deck generate() {
        return new Deck(new ArrayDeque<>(cards));
    }
}
