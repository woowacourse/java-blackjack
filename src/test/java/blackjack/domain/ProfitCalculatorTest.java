package blackjack.domain;

import blackjack.domain.result.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitCalculatorTest {

    @ParameterizedTest(name = "게임 결과 : {0}")
    @DisplayName("플래이어의 게임 결과에 따라 수익을 계산한다")
    @CsvSource(value = {"BLACKJACK_WIN,1500", "NORMAL_WIN,1000", "TIE,0", "LOSE,-1000"})
    void playerProfitTest(final GameResult result, final int expectedProfit) {
        final ProfitCalculator profitCalculator = new ProfitCalculator();
        final String name = "test";
        final int betAmount = 1000;

        profitCalculator.bet(name, betAmount);
        profitCalculator.putGameResult(name, result);
        final int actualProfit = profitCalculator.calculateProfit(name);

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

}
