package domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTypeTest {

    @ParameterizedTest
    @MethodSource("createWinningResult")
    void getResult(ResultType resultType, String expected) {
        assertThat(resultType.getResult()).isEqualTo(expected);
    }

    private static Stream<Arguments> createWinningResult() {
        return Stream.of(
                Arguments.of(ResultType.WIN, "승"),
                Arguments.of(ResultType.LOSE, "패"),
                Arguments.of(ResultType.DRAW, "무승부")
        );
    }

    @ParameterizedTest
    @DisplayName("반전된 결과")
    @MethodSource("createReversWinningResult")
    void reverse(ResultType resultType, ResultType expected) {
        assertThat(resultType.reverse()).isEqualTo(expected);
    }

    private static Stream<Arguments> createReversWinningResult() {
        return Stream.of(
                Arguments.of(ResultType.WIN, ResultType.LOSE),
                Arguments.of(ResultType.LOSE, ResultType.WIN),
                Arguments.of(ResultType.DRAW, ResultType.DRAW)
        );
    }
}