package blackjack.domain.card;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Deck {
    private static final List<Card> CARDS;

    private final Deque<Card> deck = new ArrayDeque<>();

    static {
        CARDS = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Value.values())
                        .map(value -> new Card(shape, value)))
                .collect(toList());
    }

    public Deck() {
        Collections.shuffle(CARDS);
        this.deck.addAll(CARDS);
    }

    public Cards popTwo() {
        return Stream.generate(this.deck::pop)
                .limit(2)
                .collect(collectingAndThen(toList(), Cards::new));
    }

    public Cards popOne() {
        return Stream.generate(this.deck::pop)
                .limit(1)
                .collect(collectingAndThen(toList(), Cards::new));
    }
}
