package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatchResultTest {

    @DisplayName("플레이어의 승패에 따른 딜러의 승패 개수를 계산한다.")
    @ParameterizedTest
    @MethodSource("provideForMatchResult")
    void calculateCountOfWinningResult(final Map<String, MatchStatus> winningStatuses,
                                       final Map<MatchStatus, Integer> dealerCountOfWinningResult) {
        final MatchResult matchResult = new MatchResult(winningStatuses);

        final Map<MatchStatus, Integer> count = matchResult.getResultOfDealer();
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
