package domain;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TotalDeckGenerator {
    private final List<Card> cards;

    public TotalDeckGenerator() {
        cards = Arrays.stream(Shape.values())
                .flatMap(this::makeCardsByShape)
                .collect(Collectors.toList());
    }

    public List<Card> generate() {
        Collections.shuffle(cards);
        return List.copyOf(cards);
    }

    private Stream<Card> makeCardsByShape(Shape shape) {
        return Arrays.stream(Number.values())
                .map(number -> new Card(shape, number));
    }
}
