package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.WinningType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
    @DisplayName("플레이어가_일반_승리한_경우_수익금은_원금의_두배이다")
    @Test
    void aa() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(WinningType.WIN, 10000);

        // then
        assertThat(result).isEqualTo(20000);
    }
}
