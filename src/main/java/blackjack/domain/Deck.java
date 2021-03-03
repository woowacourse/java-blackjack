package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final Deque<Card> DECK = new ArrayDeque<>();
    private static final int START_COUNT = 0;

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
                .collect(Collectors.toList());
    }

    private static void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public static Cards pop(GameStatus gameStatus) {
        int endCount = 1;
        if (gameStatus.isStart()) {
            endCount = 2;
        }
        return new Cards(IntStream.range(START_COUNT, endCount)
                .mapToObj(c -> DECK.pop())
                .collect(Collectors.toList()));
    }
}
