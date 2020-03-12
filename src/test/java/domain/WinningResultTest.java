package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningResultTest {

    @ParameterizedTest
    @MethodSource("createWinningResult")
    void getResult(WinningResult winningResult, String expected) {
        assertThat(winningResult.getResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> createWinningResult() {
        return Stream.of(
                Arguments.of(WinningResult.WIN, "승"),
                Arguments.of(WinningResult.LOSE, "패"),
                Arguments.of(WinningResult.DRAW, "무승부")
        );
    }
}