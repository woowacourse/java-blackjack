package domain.result;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeRatioTest {

    @ParameterizedTest
    @MethodSource("createWinningResult")
    void getResult(PrizeRatio prizeRatio, double expected) {
        assertThat(prizeRatio.getRatio()).isEqualTo(expected);
    }

    private static Stream<Arguments> createWinningResult() {
        return Stream.of(
                Arguments.of(PrizeRatio.WIN, 1),
                Arguments.of(PrizeRatio.LOSE, -1),
                Arguments.of(PrizeRatio.DRAW, 0),
                Arguments.of(PrizeRatio.BLACKJACK, 1.5)
        );
    }

}