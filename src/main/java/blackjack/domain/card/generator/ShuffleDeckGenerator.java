package blackjack.domain.card.generator;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardScore;
import blackjack.domain.card.Shape;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class ShuffleDeckGenerator implements DeckGenerator {

    @Override
    public Deque<Card> makeDeck() {
        final List<Card> cards = new ArrayList<>(initialize());
        Collections.shuffle(cards);
        return new ArrayDeque<>(cards);
    }

    private List<Card> initialize() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(CardScore.values())
                        .map(cardScore -> new Card(shape, cardScore)))
                .toList();
    }
}
