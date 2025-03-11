package domain.blackJack;

import static domain.blackJack.MatchResult.DRAW;
import static domain.blackJack.MatchResult.LOSE;
import static domain.blackJack.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {
    @ParameterizedTest
    @DisplayName("결과 산출 테스트")
    @MethodSource("provideSumForCalculateResultOfPlayer")
    void calculateResultOfPlayerTest(int dealerSum, int playerSum, MatchResult matchResult) {
        // given
        Result result = new Result();

        // when-then
        assertThat(result.calculateResultOfPlayer(dealerSum, playerSum)).isEqualTo(matchResult);
    }

    private static Stream<Arguments> provideSumForCalculateResultOfPlayer() {
        return Stream.of(Arguments.of(
                25, 25, LOSE,
                25, 27, LOSE,
                21, 23, LOSE,
                20, 10, LOSE,
                25, 20, WIN,
                10, 20, WIN,
                20, 20, DRAW
        ));
    }
}
