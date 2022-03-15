package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DenominationTest {
    @ParameterizedTest
    @MethodSource("getNumbers")
    @DisplayName("21에 가장 가까운 수를 구한다")
    void sumCard(int total, List<Denomination> denominations) {
        int expected = Denomination.sum(denominations);

        assertThat(expected).isEqualTo(total);
    }

    static Stream<Arguments> getNumbers() {
        return Stream.of(
                Arguments.of(16, List.of(Denomination.THREE, Denomination.FIVE, Denomination.EIGHT)),
                Arguments.of(30, List.of(Denomination.KING, Denomination.QUEEN, Denomination.JACK)),

                Arguments.of(13, List.of(Denomination.ACE, Denomination.TWO)),
                Arguments.of(21, List.of(Denomination.ACE, Denomination.TEN)),
                Arguments.of(21, List.of(Denomination.ACE, Denomination.ACE, Denomination.NINE)),
                Arguments.of(12, List.of(Denomination.ACE, Denomination.ACE, Denomination.TEN)),

                Arguments.of(18, List.of(Denomination.ACE, Denomination.SEVEN, Denomination.KING)),
                Arguments.of(20, List.of(Denomination.ACE, Denomination.ACE, Denomination.EIGHT)),
                Arguments.of(21, List.of(Denomination.ACE, Denomination.ACE, Denomination.ACE, Denomination.EIGHT)),
                Arguments.of(21, List.of(Denomination.ACE, Denomination.ACE, Denomination.NINE)),

                Arguments.of(12, List.of(Denomination.ACE, Denomination.ACE)),
                Arguments.of(13, List.of(Denomination.ACE, Denomination.ACE, Denomination.ACE)),
                Arguments.of(14, List.of(Denomination.ACE, Denomination.ACE, Denomination.ACE, Denomination.ACE))
        );
    }
}
