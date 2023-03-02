package domain;

import java.util.List;

import static java.util.stream.Collectors.*;

public class TestCardGenerator implements CardGenerator {

    private final List<Card> cards;

    private TestCardGenerator(List<Card> cards) {
        this.cards = cards;
    }

    public static TestCardGenerator from(List<Number> numbers) {
        final List<Card> cards = numbers.stream()
                .map(number -> Card.of(Shape.CLUBS, number))
                .collect(toList());

        return new TestCardGenerator(cards);
    }

    @Override
    public List<Card> shuffle() {
        return cards;
    }
}
