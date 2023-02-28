package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class NumberTest {

    @Test
    @DisplayName("전체 숫자 목록을 반환한다.")
    void getAllNumbers() {
        List<Number> expectedNumbers = List.of(Number.ACE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE, Number.SIX,
                Number.SEVEN, Number.EIGHT, Number.NINE, Number.TEN, Number.JACK, Number.QUEEN, Number.KING);

        List<Number> numbers = Number.getAll();

        assertThat(numbers).containsAll(expectedNumbers);
    }

    @ParameterizedTest
    @MethodSource("generateNumbers")
    @DisplayName("각 숫자에 해당하는 값을 확인한다.")
    void getNumberValue(Number number, int expectedValue) {
        assertThat(number.getValue()).isEqualTo(expectedValue);
    }

    static Stream<Arguments> generateNumbers() {
        return Stream.of(
                Arguments.of(Number.ACE, 11),
                Arguments.of(Number.TWO, 2),
                Arguments.of(Number.THREE, 3),
                Arguments.of(Number.FOUR, 4),
                Arguments.of(Number.FIVE, 5),
                Arguments.of(Number.SIX, 6),
                Arguments.of(Number.SEVEN, 7),
                Arguments.of(Number.EIGHT, 8),
                Arguments.of(Number.NINE, 9),
                Arguments.of(Number.TEN, 10),
                Arguments.of(Number.JACK, 10),
                Arguments.of(Number.QUEEN, 10),
                Arguments.of(Number.KING, 10)
        );
    }
}
