package blackjack.service;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import java.util.List;
import java.util.stream.IntStream;

public class MockCardsGenerator implements CardsGenerator {
    private final Card card;

    public MockCardsGenerator(Card card) {
        this.card = card;
    }

    @Override
    public Cards generate() {
        List<Card> cards = IntStream.range(0, 48)
                .mapToObj(v -> card)
                .collect(toList());
        return new Cards(cards);
    }
}
