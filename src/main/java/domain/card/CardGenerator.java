package domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface CardGenerator {

    List<Card> shuffle();

    static List<Card> generate() {
        return Arrays.stream(Suit.values())
                .flatMap(CardGenerator::giveNumber)
                .collect(Collectors.toList());
    }

    private static Stream<Card> giveNumber(final Suit suit) {
        return Arrays.stream(Rank.values())
                .map(createCard(suit));
    }

    private static Function<Rank, Card> createCard(final Suit suit) {
        return number -> Card.of(suit, number);
    }
}
