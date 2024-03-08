package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.ParticipantName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {
    @DisplayName("딜러의 승패를 통계낸다.")
    @Test
    void summarizeDealerResult() {
        WinningResult winningResult = new WinningResult(Map.of(
                new ParticipantName("kirby"), WinStatus.LOSE,
                new ParticipantName("baekho"), WinStatus.WIN));
        Map<WinStatus, Long> dealerResult = winningResult.summarizeDealerWinningResult();

        assertThat(dealerResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                WinStatus.WIN, 1L,
                WinStatus.LOSE, 1L));
    }
}
