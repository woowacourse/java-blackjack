package blackjack.domain.card;

import blackjack.domain.card.generator.DeckGenerator;
import java.util.Deque;
import java.util.stream.IntStream;

public class Deck {

    private final Deque<Card> cards;

    public Deck(final DeckGenerator deckGenerator) {
        this.cards = deckGenerator.makeDeck();
    }

    public Hand spreadCards(final int count) {
        return new Hand(IntStream.range(0, count)
                .mapToObj(o -> pickCard())
                .toList());
    }

    private Card pickCard() {
        Card card = cards.pollFirst();
        if (card == null) {
            throw new IllegalStateException("[ERROR] 카드가 더이상 없습니다.");
        }
        return card;
    }
}
