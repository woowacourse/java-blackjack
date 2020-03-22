package domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    @DisplayName("블랙잭으로 승리할 경우 수익계산")
    void calculateBlackJackWinRevenue() {
        int betAmount = 1_234;
        assertThat(Result.BLACKJACK_WIN.revenueOf(betAmount)).isEqualTo((int) (betAmount * 1.5));
    }

    @Test
    @DisplayName("승리할 경우 수익계산")
    void calculateWinRevenue() {
        int betAmount = 1_234;
        assertThat(Result.WIN.revenueOf(betAmount)).isEqualTo(betAmount);
    }

    @Test
    @DisplayName("무승부일 경우 수익계산")
    void calculateDrawRevenue() {
        int betAmount = 1_234;
        assertThat(Result.DRAW.revenueOf(betAmount)).isEqualTo(0);
    }

    @Test
    @DisplayName("패할 경우 수익계산")
    void calculateLoseRevenue() {
        int betAmount = 1_234;
        assertThat(Result.LOSE.revenueOf(betAmount)).isEqualTo(-betAmount);
    }
}
