package domain;

import domain.card.Card;
import domain.card.HandCards;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    private static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(new int[]{2, 3}, 5),
                Arguments.of(new int[]{3, 6, 11}, 19),
                Arguments.of(new int[]{1, 2, 3}, 16),
                Arguments.of(new int[]{1, 7, 9}, 17)
        );
    }

    private static List<Card> parseNumbersToCards(int... input) {
        return Arrays.stream(input)
                .mapToObj(i -> Card.of(Rank.of(i), Suit.CLOVER))
                .collect(toList());
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    void calculate(int[] numbers, int expectedScore) {
        HandCards handCards = new HandCards(parseNumbersToCards(numbers));

        assertThat(ScoreCalculator.calculate(handCards)).isEqualTo(expectedScore);
    }
}