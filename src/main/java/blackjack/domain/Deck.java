package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final List<Card> CACHE_DECK = Stream.of(Kind.values())
            .flatMap(kind -> Stream.of(Value.values()).map(value -> new Card(kind, value)))
            .collect(Collectors.toList());

    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck createShuffledDeck() {
        Collections.shuffle(CACHE_DECK);
        Deque<Card> cards = new ArrayDeque<>(CACHE_DECK);
        return new Deck(cards);
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 더 이상 없습니다");
        }
        return cards.pop();
    }
}
