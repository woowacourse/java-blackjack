package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class ShuffleDeckGenerator implements DeckGenerator {

    private static final List<Card> INITIAL_CARDS;

    static {
        INITIAL_CARDS = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(cardScore -> new Card(suit, cardScore)))
                .toList();
    }

    @Override
    public Deque<Card> makeDeck() {
        final List<Card> cards = new ArrayList<>(INITIAL_CARDS);
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }
}
