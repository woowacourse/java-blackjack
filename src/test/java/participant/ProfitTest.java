package participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("배팅 금액과 얻은 금액을 통해 수익을 계산한다.")
    void test1() {
        // given
        Money bettingMoney = Money.of(10000);
        Money earnedMoney = Money.of(20000);

        // when
        Profit profit = Profit.from(bettingMoney, earnedMoney);

        // then
        assertThat(profit.getAmount())
                .isEqualTo(earnedMoney.getAmount() - bettingMoney.getAmount());
    }

    @Test
    @DisplayName("수익 금액을 반전하여 반환한다.")
    void test2() {
        // given
        Profit profit = Profit.of(10000);

        // when
        Profit negatedProfit = profit.negate();

        // then
        assertThat(negatedProfit.getAmount())
                .isEqualTo(-10000);
    }
}
