package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultTest {

    @ParameterizedTest
    @MethodSource("provideForMatchResult")
    @DisplayName("플레이어의 승패에 따른 딜러 승패 계산")
    void calculateCountOfWinningResult(final Map<String, PlayerResult> winningResults,
                                       final Map<PlayerResult, Integer> dealerCountOfWinningResult) {
        final MatchResult matchResult = new MatchResult(winningResults);

        Map<PlayerResult, Integer> count = matchResult.getDealerResult();
        assertThat(count).isEqualTo(dealerCountOfWinningResult);
    }

    private static Stream<Arguments> provideForMatchResult() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                "sun", PlayerResult.WIN,
                                "if", PlayerResult.WIN,
                                "pobi", PlayerResult.LOSS,
                                "jun", PlayerResult.DRAW
                        ),
                        Map.of(
                                PlayerResult.WIN, 1,
                                PlayerResult.LOSS, 2,
                                PlayerResult.DRAW, 1
                        )
                )
        );
    }

}
