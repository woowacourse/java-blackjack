package blackjack;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NumberTest {
    @ParameterizedTest
    @MethodSource("getNumbers")
    @DisplayName("카드의 합을 계산한다")
    void sumCard(int total, List<Number> numbers) {
        int expected = Number.sum(numbers);

        assertThat(expected).isEqualTo(total);
    }

    static Stream<Arguments> getNumbers() {
        return Stream.of(
                Arguments.of(16, List.of(Number.THREE, Number.FIVE, Number.EIGHT)),
                Arguments.of(30, List.of(Number.KING, Number.QUEEN, Number.JACK)),
                Arguments.of(18, List.of(Number.ACE, Number.SEVEN, Number.KING))
        );
    }
}
