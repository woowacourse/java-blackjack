package blackjack.domain.card;

import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class ShuffledDeck implements Deck {
    private static final Deck DECK;

    static {
        final List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Shape.values()).map(shape -> new Card(rank, shape)))
                .collect(toList());
        Collections.shuffle(cards);
        DECK = new ShuffledDeck(new ArrayDeque<>(cards));
    }

    private final Deque<Card> cards;

    private ShuffledDeck(final Deque<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Card draw() {
        final Card card = cards.removeFirst();
        cards.addLast(card);
        return card;
    }

    public static Deck getInstance() {
        return DECK;
    }
}
