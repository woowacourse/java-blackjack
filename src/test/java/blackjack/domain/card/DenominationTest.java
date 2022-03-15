package blackjack.domain.card;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DenominationTest {

    @ParameterizedTest
    @MethodSource("generateBustCheckValues")
    void 버스트_가능여부를_반환한다(final List<Denomination> denominations, final boolean expected) {
        boolean result = Denomination.canBust(denominations);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> generateBustCheckValues() {
        return Stream.of(
                Arguments.of(Arrays.asList(THREE, SEVEN, TEN), false),
                Arguments.of(Arrays.asList(A, TEN), false),
                Arguments.of(Arrays.asList(A, A, TEN), true),
                Arguments.of(Arrays.asList(A, A, TEN, TEN), true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateCalculateScoreValues")
    void 가능한_가장_좋은_합을_계산한다(List<Denomination> cardNumbers, int expected) {
        final int result = Denomination.calculateScore(cardNumbers);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> generateCalculateScoreValues() {
        return Stream.of(
                Arguments.of(Arrays.asList(THREE, SEVEN, TEN), 20),
                Arguments.of(Arrays.asList(A, TEN), 21),
                Arguments.of(Arrays.asList(A, A, TEN), 12),
                Arguments.of(Arrays.asList(A, A, TEN, TEN), 22)
        );
    }
}
