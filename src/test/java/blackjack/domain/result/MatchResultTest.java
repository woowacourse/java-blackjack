package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {

    @ParameterizedTest
    @MethodSource("provideForMatchResult")
    @DisplayName("플레이어의 승패에 따른 딜러 승패 계산")
    void calculateCountOfWinningResult(final Map<String, MatchStatus> winningStatuses,
                                       final Map<MatchStatus, Integer> dealerCountOfWinningResult) {
        final MatchResult matchResult = new MatchResult(winningStatuses);

        Map<MatchStatus, Integer> count = matchResult.getDealerResult();
        assertThat(count).isEqualTo(dealerCountOfWinningResult);
    }

    private static Stream<Arguments> provideForMatchResult() {
        return Stream.of(
                Arguments.of(
                        Map.of(
                                "sun", MatchStatus.WIN,
                                "if", MatchStatus.WIN,
                                "pobi", MatchStatus.LOSS
                        ),
                        Map.of(
                                MatchStatus.WIN, 1,
                                MatchStatus.LOSS, 2
                        )
                )
        );
    }

}
