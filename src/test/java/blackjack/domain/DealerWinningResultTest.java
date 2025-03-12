package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.Test;

class DealerWinningResultTest {

    @Test
    void 딜러_우승_결과를_조회한다() {
        // Given
        DealerWinningResult winningResult = new DealerWinningResult(
                Map.of("밍트", ResultStatus.WIN, "엠제이", ResultStatus.LOSE));

        // When & then
        assertAll(
                () -> assertThat(winningResult.countResultStatus(ResultStatus.WIN)).isEqualTo(1),
                () -> assertThat(winningResult.countResultStatus(ResultStatus.LOSE)).isEqualTo(1),
                () -> assertThat(winningResult.countResultStatus(ResultStatus.PUSH)).isEqualTo(0)
        );
    }

    @Test
    void 플레이어의_우승_결과를_조회한다() {
        // Given
        String mint = "밍트";
        String mj = "엠제이";
        String pobi = "포비";
        DealerWinningResult winningResult = new DealerWinningResult(
                Map.of(mint, ResultStatus.WIN, mj, ResultStatus.LOSE, pobi, ResultStatus.PUSH)
        );

        // When
        Map<String, ResultStatus> playerWinningResult = winningResult.makePlayerWinningResult();

        // Then
        assertAll(
                () -> assertThat(playerWinningResult.get(mint)).isEqualTo(ResultStatus.LOSE),
                () -> assertThat(playerWinningResult.get(mj)).isEqualTo(ResultStatus.WIN),
                () -> assertThat(playerWinningResult.get(pobi)).isEqualTo(ResultStatus.PUSH)
        );
    }
}
