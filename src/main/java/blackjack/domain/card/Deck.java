package blackjack.domain.card;

import blackjack.domain.card.generator.DeckGenerator;
import java.util.Deque;
import java.util.stream.IntStream;

public final class Deck {

    private static final int INITIAL_SPREAD_SIZE = 2;

    private final Deque<Card> cards;

    public Deck(final DeckGenerator deckGenerator) {
        this.cards = deckGenerator.makeDeck();
    }

    public Card spreadOneCard() {
        return pickCard();
    }

    public Hand spreadInitialCards(final int participantsSize) {
        return new Hand(IntStream.range(0, INITIAL_SPREAD_SIZE * participantsSize)
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
