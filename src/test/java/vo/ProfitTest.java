package vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @DisplayName("비율을 통해 수익을 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 5000", "0, 0", "-1, -5000", "1.5, 7500"})
    void apply(double ratio, int expected) {
        Profit profit = new Profit(new BettingMoney(5000));
        profit.apply(ratio);
        assertThat(profit.getProfit()).isEqualTo(expected);
    }

}
