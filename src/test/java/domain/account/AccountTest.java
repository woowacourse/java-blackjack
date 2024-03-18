package domain.account;

import domain.result.ResultProfitRatio;
import domain.vo.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @DisplayName("비율을 통해 수익을 판단한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 5000", "0, 0", "-1, -5000", "1.5, 7500"})
    void applyProfit(double ratio, int expected) {
        Account account = new Account(new BettingMoney(5000));
        account.applyProfit(ResultProfitRatio.match(ratio));
        assertThat(account.getProfit()).isEqualTo(expected);
    }
}
