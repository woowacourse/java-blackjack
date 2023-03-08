package domain;

import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.Number;
import domain.card.Shape;

import java.util.List;
import java.util.stream.Collectors;

public class TestCardGenerator implements CardGenerator {

    private final List<Card> cards;

    private TestCardGenerator(List<Card> cards) {
        this.cards = cards;
    }

    public static TestCardGenerator from(List<Number> numbers) {
        final List<Card> cards = numbers.stream()
                .map(number -> Card.of(Shape.CLUBS, number))
                .collect(Collectors.toList());

        return new TestCardGenerator(cards);
    }

    @Override
    public List<Card> shuffle() {
        return cards;
    }
}
