package blackjack.blackjack.card;

import blackjack.blackjack.card.generator.DeckGenerator;
import blackjack.blackjack.card.generator.ShuffleDeckGenerator;
import blackjack.util.ExceptionMessage;
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

    public Hand drawCardsByCount(final int count) {
        return new Hand(IntStream.range(0, count)
                .mapToObj(o -> drawCard())
                .toList());
    }

    public Card drawCard() {
        Card card = cards.pollFirst();
        if (card == null) {
            throw new IllegalStateException(ExceptionMessage.makeMessage("[ERROR] 카드가 더이상 없습니다."));
        }
        return card;
    }
}
