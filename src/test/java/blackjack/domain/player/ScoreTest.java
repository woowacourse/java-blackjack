package blackjack.domain.player;

import blackjack.domain.result.ResultStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    @Test
    @DisplayName("숫자를 통해 점수를 생성한다.")
    void Score_Instance_create_with_int() {
        final int value = 10;

        final var sut = Score.from(value);

        assertThat(sut).isEqualTo(Score.from(value));
    }

    private static Stream<Arguments> maskingScoreParam() {
        return Stream.of(
                Arguments.arguments(10, 7, ResultStatus.WIN),
                Arguments.arguments(5, 5, ResultStatus.DRAW),
                Arguments.arguments(4, 6, ResultStatus.LOSE));
    }

    @ParameterizedTest(name = "{0} 과 {1} 을 비교하면 {2} 의 결과가 나온다")
    @MethodSource("maskingScoreParam")
    @DisplayName("점수를 비교해서 결과를 반환한다.")
    void Score_compare_of_score(final int value1, final int value2, final ResultStatus resultStatus) {
        final Score score1 = Score.from(value1);
        final Score score2 = Score.from(value2);

        final var result = score1.compare(score2);

        assertThat(result).isEqualTo(resultStatus);
    }

}
