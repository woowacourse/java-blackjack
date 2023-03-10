package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

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

    @Test
    @DisplayName("플레이어들의 수익 총합을 통해 딜러의 수익을 계산한다")
    void dealerProfitTest() {
        final ProfitCalculator profitCalculator = new ProfitCalculator();
        final List<String> names = List.of("test1", "test2", "test3");
        final List<Integer> betAmounts = List.of(1000, 2000, 5000);
        final List<GameResult> gameResults = List.of(GameResult.TIE, GameResult.LOSE, GameResult.BLACKJACK_WIN);
        final int expectedResult = -5500;

        for (int index = 0; index < names.size(); index++) {
            profitCalculator.bet(names.get(index), betAmounts.get(index));
            profitCalculator.putGameResult(names.get(index), gameResults.get(index));
        }
        final int dealerProfit = profitCalculator.calculateDealerProfit();

        assertThat(dealerProfit).isEqualTo(expectedResult);
    }

}
