package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Suit.HEARTS;
import static blackjack.domain.card.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DenominationTest {

    @ParameterizedTest
    @MethodSource("generateMaxCalculateScoreValues")
    void 가능한_가장_큰_합을_계산(final List<Card> cards, final int expected) {
        final Score result = Denomination.calculateCardMaxScore(cards);
        assertThat(result).isEqualTo(new Score(expected));
    }

    private static Stream<Arguments> generateMaxCalculateScoreValues() {
        return Stream.of(
                Arguments.of(List.of(new Card(SPADES, THREE), new Card(SPADES, SEVEN), new Card(SPADES, TEN)), 20),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, TEN)), 21),
                Arguments.of(List.of(new Card(SPADES, A), new Card(HEARTS, A)), 22),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, THREE), new Card(SPADES, EIGHT)), 22),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, EIGHT)), 19)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCalculateScoreValues")
    void 가능한_가장_좋은_합을_계산(List<Card> cards, int expected) {
        final Score result = Denomination.calculateCardScore(cards);
        assertThat(result).isEqualTo(new Score(expected));
    }

    private static Stream<Arguments> generateCalculateScoreValues() {
        return Stream.of(
                Arguments.of(List.of(new Card(SPADES, THREE), new Card(SPADES, SEVEN), new Card(SPADES, TEN)), 20),
                Arguments.of(List.of(new Card(SPADES, A), new Card(SPADES, TEN)), 21),
                Arguments.of(List.of(new Card(SPADES, A), new Card(HEARTS, A), new Card(HEARTS, TEN)), 12),
                Arguments.of(List.of(
                        new Card(SPADES, A), new Card(HEARTS, A), new Card(SPADES, TEN), new Card(HEARTS, TEN)), 22)
        );
    }
}
