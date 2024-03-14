package blackjack.domain.deck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Deck {

    private static final List<Card> CACHE_DECK = Arrays.stream(Kind.values())
            .flatMap(kind -> Arrays.stream(Value.values())
                    .map(value -> new Card(kind, value)))
            .collect(Collectors.toList());

    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck createShuffledDeck() {
        Collections.shuffle(CACHE_DECK);
        return new Deck(new ArrayDeque<>(CACHE_DECK));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 더이상 없습니다.");
        }
        return cards.pop();
    }
}
