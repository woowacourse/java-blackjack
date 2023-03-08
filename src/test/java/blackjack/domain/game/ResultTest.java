package blackjack.domain.game;

import blackjack.domain.participant.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class ResultTest {

    @ParameterizedTest(name = "playerScore={0}, dealerScore={1}, expected={2}")
    @MethodSource("scoreDummy")
    @DisplayName("플레이어의 결과를 계산한다.")
    void calculatePlayerRest(int playerScoreValue, int dealerScoreValue, Result expected) {
        Score playerScore = new Score(playerScoreValue);
        Score dealerScore = new Score(dealerScoreValue);

        assertThat(Result.calculatePlayerResult(playerScore, dealerScore)).isEqualTo(expected);
    }

    static Stream<Arguments> scoreDummy() {
        return Stream.of(
                Arguments.of(18, 17, Result.WIN),
                Arguments.of(17, 17, Result.DRAW),
                Arguments.of(17, 18, Result.LOSE),
                Arguments.of(23, 17, Result.LOSE),
                Arguments.of(21, 21, Result.DRAW),
                Arguments.of(23, 23, Result.LOSE)
        );
    }

    @Test
    @DisplayName("결과의 반대 값을 반환한다")
    void oppositeResult() {
        assertThat(Result.oppositeResult(Result.WIN)).isEqualTo(Result.LOSE);
        assertThat(Result.oppositeResult(Result.LOSE)).isEqualTo(Result.WIN);
        assertThat(Result.oppositeResult(Result.DRAW)).isEqualTo(Result.DRAW);
    }
}
