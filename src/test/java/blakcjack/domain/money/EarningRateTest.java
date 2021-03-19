package blakcjack.domain.money;

import blakcjack.domain.outcome.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {
    @DisplayName("블랙잭으로 승리한 경우")
    @Test
    void of_blackjack() {
        final EarningRate earningRate = EarningRate.of(Outcome.WIN, true);
        assertThat(earningRate).isEqualTo(EarningRate.BLACKJACK);
    }

    @DisplayName("승리했지만 블랙잭은 아닌 경우")
    @Test
    void of_win() {
        final EarningRate earningRate = EarningRate.of(Outcome.WIN, false);
        assertThat(earningRate).isEqualTo(EarningRate.WIN);
    }

    @DisplayName("비긴 경우")
    @Test
    void of_draw() {
        final EarningRate earningRate = EarningRate.of(Outcome.DRAW, true);
        assertThat(earningRate).isEqualTo(EarningRate.DRAW);
    }

    @DisplayName("진 경우")
    @Test
    void of_lose() {
        final EarningRate earningRate = EarningRate.of(Outcome.LOSE, false);
        assertThat(earningRate).isEqualTo(EarningRate.LOSE);
    }
}
