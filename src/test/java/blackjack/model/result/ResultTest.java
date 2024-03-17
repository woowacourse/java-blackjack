package blackjack.model.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.model.results.Result;
import blackjack.vo.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {
    @DisplayName("게임 결과에 따라 수익이 결정된다")
    @ParameterizedTest
    @CsvSource(value = {"WIN_BY_BLACKJACK, 1000, 1500", "WIN,1000,1000", "PUSH,1000,0", "LOSE,1000,-1000,"})
    void calculateProfit(Result gameResult, int betMoney, int expectedProfit) {
        Money betAmount = new Money(betMoney);
        Money profit = gameResult.calculateProfit(betAmount);

        assertThat(profit).isEqualTo(new Money(expectedProfit));
    }
}
