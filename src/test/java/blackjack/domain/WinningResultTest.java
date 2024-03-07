package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningResultTest {
    @Test
    @DisplayName("딜러의 승패를 통계낸다.")
    void summarizeDealerResult() {
        WinningResult winningResult = new WinningResult(Map.of(
                new ParticipantName("kirby"), WinStatus.LOSE,
                new ParticipantName("baekho"), WinStatus.WIN));
        Map<WinStatus, Integer> dealerResult = winningResult.summarizeDealerResult();

        assertThat(dealerResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                WinStatus.WIN, 1,
                WinStatus.LOSE, 1));
    }
}
