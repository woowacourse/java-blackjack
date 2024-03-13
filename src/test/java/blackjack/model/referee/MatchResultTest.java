package blackjack.model.referee;

import blackjack.model.card.Score;
import blackjack.model.result.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultTest {
    @ParameterizedTest
    @MethodSource("provideScoreAndExpectedMatchResult")
    @DisplayName("Score(점수)에 따라 승패를 결정한다.")
    public void decideByScoreTest(Score targetScore, MatchResult expectedResult) {
        // given
        Score otherScore = Score.from(9);

        // when
        MatchResult actualResult = MatchResult.decideByScore(targetScore, otherScore);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideScoreAndExpectedMatchResult() {
        return Stream.of(
                Arguments.of(Score.from(10), MatchResult.WIN),
                Arguments.of(Score.from(8), MatchResult.LOSE),
                Arguments.of(Score.from(9), MatchResult.PUSH)
        );
    }
}
