package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Number;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

class NumberTest {
    @ParameterizedTest
    @MethodSource("getNumbers")
    @DisplayName("21에 가장 가까운 수를 구한다")
    void sumCard(int total, List<Number> numbers) {
        int expected = Number.sum(numbers);

        assertThat(expected).isEqualTo(total);
    }

    static Stream<Arguments> getNumbers() {
        return Stream.of(
                Arguments.of(16, List.of(Number.THREE, Number.FIVE, Number.EIGHT)),
                Arguments.of(30, List.of(Number.KING, Number.QUEEN, Number.JACK)),

                Arguments.of(13, List.of(Number.ACE, Number.TWO)),
                Arguments.of(21, List.of(Number.ACE, Number.TEN)),
                Arguments.of(21, List.of(Number.ACE, Number.ACE, Number.NINE)),
                Arguments.of(12, List.of(Number.ACE, Number.ACE, Number.TEN)),

                Arguments.of(18, List.of(Number.ACE, Number.SEVEN, Number.KING)),
                Arguments.of(20, List.of(Number.ACE, Number.ACE, Number.EIGHT)),
                Arguments.of(21, List.of(Number.ACE, Number.ACE, Number.ACE, Number.EIGHT)),
                Arguments.of(21, List.of(Number.ACE, Number.ACE, Number.NINE)),

                Arguments.of(12, List.of(Number.ACE, Number.ACE)),
                Arguments.of(13, List.of(Number.ACE, Number.ACE, Number.ACE)),
                Arguments.of(14, List.of(Number.ACE, Number.ACE, Number.ACE, Number.ACE))
        );
    }
}
