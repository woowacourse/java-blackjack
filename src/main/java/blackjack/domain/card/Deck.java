package blackjack.domain.card;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Deck {
    private static final Deque<Card> DECK = new ArrayDeque<>();

    static {
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Shape.values())
                .forEach(shape -> cards.addAll(createByShape(shape)));
        shuffle(cards);
        DECK.addAll(cards);
    }

    private static List<Card> createByShape(Shape shape) {
        return Arrays.stream(Value.values())
                .map(value->new Card(shape, value))
                .collect(toList());
    }

    private static void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public static Cards popTwo() {
        return Stream.generate(DECK::pop)
                .limit(2)
                .collect(collectingAndThen(toList(), Cards::new));
    }

    public static Cards popOne() {
        return Stream.generate(DECK::pop)
                .limit(1)
                .collect(collectingAndThen(toList(), Cards::new));
    }
}
