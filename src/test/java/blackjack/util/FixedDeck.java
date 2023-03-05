package blackjack.util;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FixedDeck implements Deck {

    private final Deque<Card> cards;

    public FixedDeck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public FixedDeck(final Card... cards) {
        this.cards = Arrays.stream(cards)
                .collect(collectingAndThen(toList(), ArrayDeque::new));
    }

    @Override
    public Card draw() {
        return cards.removeFirst();
    }
}
