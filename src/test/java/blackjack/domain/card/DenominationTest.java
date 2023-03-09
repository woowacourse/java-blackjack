package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DenominationTest {

    @Test
    @DisplayName("전체 숫자 목록을 반환한다.")
    void getAllNumbers() {
        List<Denomination> expectedDenominations = List.of(Denomination.ACE, Denomination.TWO, Denomination.THREE, Denomination.FOUR, Denomination.FIVE, Denomination.SIX,
                Denomination.SEVEN, Denomination.EIGHT, Denomination.NINE, Denomination.TEN, Denomination.JACK, Denomination.QUEEN, Denomination.KING);

        List<Denomination> denominations = Denomination.getAll();

        assertThat(denominations).containsAll(expectedDenominations);
    }

    @ParameterizedTest(name = "denomination={0}, expectedValue={1}")
    @MethodSource("generateNumbers")
    @DisplayName("각 숫자에 해당하는 값을 확인한다.")
    void getNumberValue(Denomination denomination, int expectedValue) {
        assertThat(denomination.getValue()).isEqualTo(expectedValue);
    }

    static Stream<Arguments> generateNumbers() {
        return Stream.of(
                Arguments.of(Denomination.ACE, 11),
                Arguments.of(Denomination.TWO, 2),
                Arguments.of(Denomination.THREE, 3),
                Arguments.of(Denomination.FOUR, 4),
                Arguments.of(Denomination.FIVE, 5),
                Arguments.of(Denomination.SIX, 6),
                Arguments.of(Denomination.SEVEN, 7),
                Arguments.of(Denomination.EIGHT, 8),
                Arguments.of(Denomination.NINE, 9),
                Arguments.of(Denomination.TEN, 10),
                Arguments.of(Denomination.JACK, 10),
                Arguments.of(Denomination.QUEEN, 10),
                Arguments.of(Denomination.KING, 10)
        );
    }
}
