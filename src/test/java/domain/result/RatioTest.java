package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RatioTest {

    @ParameterizedTest
    @MethodSource("createWinningResult")
    void getResult(Ratio ratio, double expected) {
        assertThat(ratio.getRatio()).isEqualTo(expected);
    }

    private static Stream<Arguments> createWinningResult() {
        return Stream.of(
                Arguments.of(Ratio.WIN, 1),
                Arguments.of(Ratio.LOSE, -1),
                Arguments.of(Ratio.DRAW, 0),
                Arguments.of(Ratio.BLACKJACK, 1.5)
        );
    }

}