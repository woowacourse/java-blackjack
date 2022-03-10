package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest
    @MethodSource("provideScoreAndResult")
    @DisplayName("점수에 따른 승무패를 계산한다.")
    void findResult(Result result, int myScore, int otherScore) {
        final Result actual = Result.findResult(myScore, otherScore);

        assertThat(actual).isEqualTo(result);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(Result.DRAW, 21, 21),
                Arguments.of(Result.DRAW, 23, 22),
                Arguments.of(Result.DRAW, 22, 23),
                Arguments.of(Result.WIN, 21, 20),
                Arguments.of(Result.WIN, 21, 22),
                Arguments.of(Result.LOSE, 20, 21),
                Arguments.of(Result.LOSE, 22, 21)
        );
    }
}
