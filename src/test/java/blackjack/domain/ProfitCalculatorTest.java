package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.WinningType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitCalculatorTest {
    @DisplayName("플레이어가_블랙잭_승리한_경우_수익금은_원금의_1.5배다")
    @CsvSource(value = {"BLACKJACK_WIN:15_000", "WIN:20_000", "DEFEAT:-10_000", "DRAW:10_000"}, delimiterString = ":")
    @ParameterizedTest
    void calculatePlayerProfit(WinningType type, int expected) {
        // given
        ProfitCalculator profitCalculator = new ProfitCalculator();

        // when
        int result = profitCalculator.calculatePlayerProfit(type, 10_000);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
