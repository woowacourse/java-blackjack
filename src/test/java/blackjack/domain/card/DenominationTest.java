package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Suit.HEARTS;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DenominationTest {

    @ParameterizedTest
    @MethodSource("generateMaxCalculateScoreValues")
    void 가능한_가장_큰_합을_계산(final Set<Card> cards, final int expected) {
        final int result = Denomination.calculateCardMaxScore(cards);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> generateMaxCalculateScoreValues() {
        return Stream.of(
                Arguments.of(Set.of(Card.of(SPADES, THREE), Card.of(SPADES, SEVEN), Card.of(SPADES, TEN)), 20),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(SPADES, TEN)), 21),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(HEARTS, A)), 22),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(SPADES, THREE), Card.of(SPADES, EIGHT)), 22),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(SPADES, EIGHT)), 19)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCalculateScoreValues")
    void 가능한_가장_좋은_합을_계산(Set<Card> cards, int expected) {
        final int result = Denomination.calculateCardScore(cards);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> generateCalculateScoreValues() {
        return Stream.of(
                Arguments.of(Set.of(Card.of(SPADES, THREE), Card.of(SPADES, SEVEN), Card.of(SPADES, TEN)), 20),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(SPADES, TEN)), 21),
                Arguments.of(Set.of(Card.of(SPADES, A), Card.of(HEARTS, A), Card.of(HEARTS, TEN)), 12),
                Arguments.of(Set.of(
                        Card.of(SPADES, A), Card.of(HEARTS, A), Card.of(SPADES, TEN), Card.of(HEARTS, TEN)), 22)
        );
    }
}
