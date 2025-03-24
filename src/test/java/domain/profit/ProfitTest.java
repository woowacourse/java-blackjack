package domain.profit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitTest {

    @ParameterizedTest
    @DisplayName("베팅금액을 승패에 따라 올바른 수익을 반환")
    @CsvSource({
            "WIN_BLACKJACK, 15000",
            "WIN, 10000",
            "DRAW, 0",
            "LOSE, -10000"
    })
    void validProfit(final Profit profit, final long expectedProfit) {
        final long betAmount = 10000L;
        final long calculatedProfit = profit.calculate(betAmount);
        assertThat(calculatedProfit).isEqualTo(expectedProfit);
    }
}
