package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    @DisplayName("결과에 따라 수익을 계산한다.")
    void 수익_계산_테스트() {
        // given
        final int betAmount = 100_000;

        // when & then
        assertThat(GameResult.BLACKJACK.calculateBetProfit(betAmount)).isEqualTo(150_000);
        assertThat(GameResult.WIN.calculateBetProfit(betAmount)).isEqualTo(100_000);
        assertThat(GameResult.DRAW.calculateBetProfit(betAmount)).isEqualTo(0);
        assertThat(GameResult.LOSE.calculateBetProfit(betAmount)).isEqualTo(-100_000);
    }
}
