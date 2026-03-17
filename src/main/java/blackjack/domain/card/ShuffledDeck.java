package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledDeck implements Deck {
    private final Queue<Card> cards;

    public ShuffledDeck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    public static ShuffledDeck create() {
        List<Card> cards = Arrays.stream(Rank.values())
            .flatMap(ShuffledDeck::createCardsByRank)
            .collect(Collectors.toCollection(ArrayList::new));
        return new ShuffledDeck(cards);
    }

    private static Stream<Card> createCardsByRank(Rank rank) {
        return Arrays.stream(Suit.values())
            .map(suit -> new Card(rank, suit));
    }

    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱이 비어 있습니다.");
        }
        return cards.poll();
    }
}
