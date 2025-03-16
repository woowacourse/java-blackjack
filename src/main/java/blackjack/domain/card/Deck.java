package blackjack.domain.card;

import blackjack.domain.card.generator.DeckGenerator;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import java.util.Deque;
import java.util.stream.IntStream;

public final class Deck {

    private final Deque<Card> cards;

    public Deck(final DeckGenerator deckGenerator) {
        this.cards = deckGenerator.makeDeck();
    }

    public static Deck shuffled() {
        return new Deck(new ShuffleDeckGenerator());
    }

    public Hand spreadInitialCards(final int cardSize) {
        return new Hand(IntStream.range(0, cardSize)
                .mapToObj(o -> pickCard())
                .toList());
    }

    public Card spreadOneCard() {
        return pickCard();
    }

    private Card pickCard() {
        Card card = cards.pollFirst();
        if (card == null) {
            throw new IllegalStateException("[ERROR] 카드가 더이상 없습니다.");
        }
        return card;
    }
}
