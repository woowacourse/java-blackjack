package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface CardGenerator {

    List<Card> shuffle();

    static List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap(CardGenerator::giveNumber)
                .collect(Collectors.toList());
    }

    private static Stream<Card> giveNumber(final Shape shape) {
        return Arrays.stream(Number.values())
                .map(createCard(shape));
    }

    private static Function<Number, Card> createCard(final Shape shape) {
        return number -> Card.of(shape, number);
    }
}
