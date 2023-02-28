package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface CardGenerator {
    List<Card> shuffle();

    default List<Card> generate() {
        return Arrays.stream(Shape.values())
                .flatMap(this::giveNumber)
                .collect(Collectors.toList());
    }

    private Stream<Card> giveNumber(final Shape i) {
        return Arrays.stream(Number.values())
                .map(createCard(i));
    }

    private Function<Number, Card> createCard(final Shape i) {
        return j -> Card.of(i, j);
    }
}
