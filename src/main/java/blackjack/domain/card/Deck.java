package blackjack.domain.card;

import blackjack.domain.user.Cards;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final List<Card> CACHE_CARD;
    private static final int START_COUNT = 0;

    private final Queue<Card> deck;

    static {
        CACHE_CARD = new ArrayList<>();
        Arrays.stream(Suit.values())
                .forEach(shape -> CACHE_CARD.addAll(createByShape(shape)));
    }

    private static List<Card> createByShape(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(value -> new Card(suit, value))
                .collect(Collectors.toList());
    }

    public Deck() {
        Collections.shuffle(CACHE_CARD);
        this.deck = new ArrayDeque<>(CACHE_CARD);
    }

    public Cards popToInitialCards() {
        return new Cards(IntStream.range(START_COUNT, 2)
                .mapToObj(c -> deck.poll())
                .collect(Collectors.toList()));
    }

    public Card popOne() {
        return deck.poll();
    }
}
