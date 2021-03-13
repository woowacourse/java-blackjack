package blackjack.domain.card;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Deck {
    private final Deque<Card> deck;

    public Deck() {
        this.deck = new ArrayDeque<>(setUpDeck());
    }

    private List<Card> setUpDeck() {
        List<Card> cards = Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Value.values())
                        .map(value -> new Card(shape, value)))
                .collect(toList());
        shuffle(cards);
        return cards;
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
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
