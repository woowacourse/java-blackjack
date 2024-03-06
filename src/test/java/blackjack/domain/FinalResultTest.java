package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinalResultTest {
    @Test
    @DisplayName("딜러의 승패를 통계낸다.")
    void summarizeDealerResult() {
        FinalResult finalResult = new FinalResult(Map.of(
                new PlayerName("kirby"), WinStatus.LOSE,
                new PlayerName("baekho"), WinStatus.WIN));
        Map<WinStatus, Integer> dealerResult = finalResult.summarizeDealerResult();

        assertThat(dealerResult).containsExactlyInAnyOrderEntriesOf(Map.of(
                WinStatus.WIN, 1,
                WinStatus.LOSE, 1));
    }
}
