package blackjack.domain;

import static blackjack.view.WinningType.BLACKJACK_WIN;
import static blackjack.view.WinningType.DEFEAT;
import static blackjack.view.WinningType.DRAW;
import static blackjack.view.WinningType.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
    @DisplayName("플레이어가_블랙잭_승리한_경우_수익금은_원금의_1.5배다")
    @Test
    void a() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(BLACKJACK_WIN, 10000);

        // then
        assertThat(result).isEqualTo(15000);
    }

    @DisplayName("플레이어가_일반_승리한_경우_수익금은_원금의_2배다")
    @Test
    void aa() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(WIN, 10000);

        // then
        assertThat(result).isEqualTo(20000);
    }

    @DisplayName("플레이어가_패배한_경우_원금을_잃는다")
    @Test
    void aaa() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(DEFEAT, 10000);

        // then
        assertThat(result).isEqualTo(-20000);
    }

    @DisplayName("플레이어가_무승부한_경우_원금만을_회수한다")
    @Test
    void aaaa() {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(DRAW, 10000);

        // then
        assertThat(result).isEqualTo(10000);
    }
}
