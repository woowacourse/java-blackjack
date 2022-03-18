package blackjack.domain.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.result.WinningResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EarningRateCalculatorTest {

    @Test
    @DisplayName("블랙잭인 경우 1.5배를 반환한다.")
    void calculatePlayerBlackjackEarningRate() {
        WinningResult winningResult = WinningResult.BLACKJACK;

        EarningRateCalculator earningRateCalculator = new EarningRateCalculator();

        assertThat(earningRateCalculator.calculate(winningResult)).isEqualTo(1.5);
    }

    @Test
    @DisplayName("무승부인 경우 0배를 반환한다.")
    void calculatePlayerDrawEarningRate() {
        WinningResult winningResult = WinningResult.DRAW;

        EarningRateCalculator earningRateCalculator = new EarningRateCalculator();

        assertThat(earningRateCalculator.calculate(winningResult)).isEqualTo(0);
    }

    @Test
    @DisplayName("블랙잭이 아니면서 승리인 경우 1배를 반환한다.")
    void calculatePlayerWinEarningRate() {
        WinningResult winningResult = WinningResult.WIN;

        EarningRateCalculator earningRateCalculator = new EarningRateCalculator();

        assertThat(earningRateCalculator.calculate(winningResult)).isEqualTo(1);
    }

    @Test
    @DisplayName("패배인 경우 -1배를 반환한다.")
    void calculatePlayerLoseEarningRate() {
        WinningResult winningResult = WinningResult.LOSE;

        EarningRateCalculator earningRateCalculator = new EarningRateCalculator();

        assertThat(earningRateCalculator.calculate(winningResult)).isEqualTo(-1);
    }

}