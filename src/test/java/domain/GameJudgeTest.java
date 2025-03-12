package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GameJudgeTest {
    static Stream<Arguments> createPlayerScores() {
        return Stream.of(
                Arguments.of(new Score(21), new Score(10), WinStatus.WIN),
                Arguments.of(new Score(21), new Score(20), WinStatus.WIN),
                Arguments.of(new Score(21), new Score(21), WinStatus.DRAW),
                Arguments.of(new Score(10), new Score(10), WinStatus.DRAW),
                Arguments.of(new Score(10), new Score(21), WinStatus.LOSE),
                Arguments.of(new Score(20), new Score(21), WinStatus.LOSE)
        );
    }

    @ParameterizedTest
    @MethodSource("createPlayerScores")
    void 점수를_기준으로_승패를_판별한다(Score player, Score otherPlayer, WinStatus expected) {
        // given
        GameJudge gameJudge = new GameJudge(player, otherPlayer);

        // when
        WinStatus actual = gameJudge.getResult();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
